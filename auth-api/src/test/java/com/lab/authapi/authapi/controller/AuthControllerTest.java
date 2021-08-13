package com.lab.authapi.authapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.authapi.authapi.dto.AuthRequest;
import com.lab.authapi.authapi.dto.CreateUserRequest;
import com.lab.authapi.authapi.dto.UserView;
import com.lab.authapi.authapi.helper.UserTestDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.lab.authapi.authapi.helper.Util.fromJson;
import static com.lab.authapi.authapi.helper.Util.toJson;
import static java.lang.System.currentTimeMillis;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final UserTestDataFactory userTestDataFactory;

    private final String password = "Test12345_";

    @Autowired
    public AuthControllerTest(MockMvc mockMvc, ObjectMapper objectMapper, UserTestDataFactory userTestDataFactory) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.userTestDataFactory = userTestDataFactory;
    }

    @Test
    public void testLoginSuccess() throws Exception {
        UserView userView = userTestDataFactory.createUser(String.format("test.user.%d@example.com", currentTimeMillis()), "Test User", password);

        AuthRequest request = new AuthRequest();
        request.setUsername(userView.getUsername());
        request.setPassword(password);

        MvcResult createResult = this.mockMvc
                .perform(post("/v1.0/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, request)))
                .andExpect(status().isOk())
                .andExpect(header().exists(HttpHeaders.AUTHORIZATION))
                .andReturn();

        UserView authUserView = fromJson(objectMapper, createResult.getResponse().getContentAsString(), UserView.class);
        assertEquals(userView.getId(), authUserView.getId(), "User ids must match!");
    }

    @Test
    public void testLoginFail() throws Exception {
        UserView userView = userTestDataFactory.createUser(String.format("test.user.%d@example.com", currentTimeMillis()), "Test User", password);

        AuthRequest request = new AuthRequest();
        request.setUsername(userView.getUsername());
        request.setPassword("zxc");

        this.mockMvc
                .perform(post("/v1.0/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, request)))
                .andExpect(status().isUnauthorized())
                .andExpect(header().doesNotExist(HttpHeaders.AUTHORIZATION))
                .andReturn();
    }

    @Test
    public void testRegisterSuccess() throws Exception {
        CreateUserRequest goodRequest = new CreateUserRequest();
        goodRequest.setUsername(String.format("test.user.%d", currentTimeMillis()));
        goodRequest.setFullName("Test User A");
        goodRequest.setPassword(password);
        goodRequest.setRePassword(password);
        goodRequest.setEmail(String.format("test.user.%d@example.com", currentTimeMillis()));

        MvcResult createResult = this.mockMvc
                .perform(post("/v1.0/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, goodRequest)))
                .andExpect(status().isOk())
                .andReturn();

        UserView userView = fromJson(objectMapper, createResult.getResponse().getContentAsString(), UserView.class);
        assertNotNull(userView.getId(), "User id must not be null!");
        assertEquals(goodRequest.getFullName(), userView.getFullName(), "User fullname  update isn't applied!");
    }

    @Test
    public void testRegisterFail() throws Exception {
        CreateUserRequest badRequest = new CreateUserRequest();
        badRequest.setUsername("invalid.username");

        this.mockMvc
                .perform(post("/v1.0/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, badRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Method argument validation failed")));
    }

}