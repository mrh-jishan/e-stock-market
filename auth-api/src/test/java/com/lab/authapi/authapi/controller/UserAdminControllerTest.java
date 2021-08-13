package com.lab.authapi.authapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.authapi.authapi.dto.CreateUserRequest;
import com.lab.authapi.authapi.dto.UpdateUserRequest;
import com.lab.authapi.authapi.dto.UserView;
import com.lab.authapi.authapi.helper.UserTestDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.lab.authapi.authapi.helper.Util.fromJson;
import static com.lab.authapi.authapi.helper.Util.toJson;
import static java.lang.System.currentTimeMillis;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithUserDetails("ada.lovelace")
@AutoConfigureMockMvc
@SpringBootTest
class UserAdminControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final UserTestDataFactory userTestDataFactory;

    @Autowired
    UserAdminControllerTest(MockMvc mockMvc, ObjectMapper objectMapper, UserTestDataFactory userTestDataFactory) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.userTestDataFactory = userTestDataFactory;
    }


    @Test
    public void testCreateSuccess() throws Exception {
        CreateUserRequest goodRequest = new CreateUserRequest();
        String username = String.format("test.user.%d", currentTimeMillis());
        goodRequest.setUsername(username);
        goodRequest.setFullName("Test User A");
        goodRequest.setPassword("Test12345_");
        goodRequest.setRePassword("Test12345_");
        goodRequest.setEmail(username + "@example.com");

        MvcResult createResult = this.mockMvc
                .perform(post("/v1.0/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, goodRequest)))
                .andExpect(status().isOk())
                .andReturn();

        UserView userView = fromJson(objectMapper, createResult.getResponse().getContentAsString(), UserView.class);
        assertNotNull(userView.getId(), "User id must not be null!");
        assertEquals(goodRequest.getFullName(), userView.getFullName(), "User fullname  update isn't applied!");
    }

    @Test
    public void testCreateFail() throws Exception {
        CreateUserRequest badRequest = new CreateUserRequest();
        badRequest.setUsername("invalid.username");

        this.mockMvc
                .perform(post("/v1.0/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, badRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Method argument validation failed")));
    }

    @Test
    public void testCreateUsernameExists() throws Exception {
        UserView userView = userTestDataFactory.createUser(String.format("test.user.%d", currentTimeMillis()), "Test User A");

        CreateUserRequest badRequest = new CreateUserRequest();
        badRequest.setUsername(userView.getUsername());
        badRequest.setFullName("Test User A");
        badRequest.setPassword("Test12345_");
        badRequest.setRePassword("Test12345_");
        badRequest.setEmail(userView.getEmail());

        this.mockMvc
                .perform(post("/v1.0/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, badRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Username exists")));
    }

    @Test
    public void testCreatePasswordsMismatch() throws Exception {
        CreateUserRequest badRequest = new CreateUserRequest();
        String username = String.format("test.user.%d", currentTimeMillis());
        badRequest.setUsername(username);
        badRequest.setFullName("Test User A");
        badRequest.setPassword("Test12345_");
        badRequest.setRePassword("Test12345");
        badRequest.setEmail(username + "@example.com");

        this.mockMvc
                .perform(post("/v1.0/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, badRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Passwords don't match")));
    }

    @Test
    public void testEditSuccess() throws Exception {
        UserView userView = userTestDataFactory.createUser(String.format("test.user.%d", currentTimeMillis()), "Test User A");

        UpdateUserRequest updateRequest = new UpdateUserRequest();
        updateRequest.setFullName("Test User B");

        MvcResult updateResult = this.mockMvc
                .perform(put(String.format("/v1.0/users/%s", userView.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, updateRequest)))
                .andExpect(status().isOk())
                .andReturn();
        UserView newUserView = fromJson(objectMapper, updateResult.getResponse().getContentAsString(), UserView.class);

        assertEquals(updateRequest.getFullName(), newUserView.getFullName(), "User fullname update isn't applied!");
    }

    @Test
    public void testEditFailBadRequest() throws Exception {
        UserView userView = userTestDataFactory.createUser(String.format("test.user.%d", currentTimeMillis()), "Test User A");

        UpdateUserRequest updateRequest = new UpdateUserRequest();

        this.mockMvc
                .perform(put(String.format("/v1.0/users/%s", userView.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, updateRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Method argument validation failed")));
    }

    @Test
    public void testEditFailNotFound() throws Exception {
        UpdateUserRequest updateRequest = new UpdateUserRequest();
        updateRequest.setFullName("Test User B");

        this.mockMvc
                .perform(put(String.format("/v1.0/users/%s", "5f07c259ffb98843e36a2aa9"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, updateRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Entity User with id 5f07c259ffb98843e36a2aa9 not found")));
    }

    @Test
    public void testDeleteSuccess() throws Exception {
        UserView userView = userTestDataFactory.createUser(String.format("test.user.%d", currentTimeMillis()), "Test User A");

        this.mockMvc
                .perform(delete(String.format("/v1.0/users/%s", userView.getId())))
                .andExpect(status().isOk());

        this.mockMvc
                .perform(get(String.format("/v1.0/users/%s", userView.getId())))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteFailNotFound() throws Exception {
        this.mockMvc
                .perform(delete(String.format("/v1.0/users/%s", "5f07c259ffb98843e36a2aa9")))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Entity User with id 5f07c259ffb98843e36a2aa9 not found")));
    }

    @Test
    public void testDeleteAndCreateAgain() throws Exception {
        UserView userView = userTestDataFactory.createUser(String.format("test.user.%d", currentTimeMillis()), "Test User A");

        this.mockMvc
                .perform(delete(String.format("/v1.0/users/%s", userView.getId())))
                .andExpect(status().isOk());

        this.mockMvc
                .perform(get(String.format("/v1.0/users/%s", userView.getId())))
                .andExpect(status().isNotFound());

        CreateUserRequest goodRequest = new CreateUserRequest();
        goodRequest.setUsername(userView.getUsername());
        goodRequest.setFullName("Test User A");
        goodRequest.setPassword("Test12345_");
        goodRequest.setRePassword("Test12345_");
        goodRequest.setEmail(userView.getEmail());

        MvcResult createResult = this.mockMvc
                .perform(post("/v1.0/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, goodRequest)))
                .andExpect(status().isOk())
                .andReturn();

        UserView newUserView = fromJson(objectMapper, createResult.getResponse().getContentAsString(), UserView.class);
        assertNotEquals(userView.getId(), newUserView.getId(), "User ids must not match!");
        assertEquals(userView.getUsername(), newUserView.getUsername(), "User names must match!");
    }

    @Test
    public void testGetSuccess() throws Exception {
        UserView userView = userTestDataFactory.createUser(String.format("test.user.%d", currentTimeMillis()), "Test User A");

        MvcResult getResult = this.mockMvc
                .perform(get(String.format("/v1.0/users/%s", userView.getId())))
                .andExpect(status().isOk())
                .andReturn();

        UserView newUserView = fromJson(objectMapper, getResult.getResponse().getContentAsString(), UserView.class);

        assertEquals(userView.getId(), newUserView.getId(), "User ids must be equal!");
    }

    @Test
    public void testGetNotFound() throws Exception {
        this.mockMvc
                .perform(get(String.format("/v1.0/users/%s", "5f07c259ffb98843e36a2aa9")))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Entity User with id 5f07c259ffb98843e36a2aa9 not found")));
    }

}