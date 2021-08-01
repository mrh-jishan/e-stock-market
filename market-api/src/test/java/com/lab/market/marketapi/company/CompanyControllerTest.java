package com.lab.market.marketapi.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.market.marketapi.util.TestDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CompanyController.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @BeforeEach
    void setup() {
        when(companyService.getAllCompanies()).thenReturn(TestDataProvider.mockCompanyList());
        when(companyService.getCompany(eq("xxx1"))).thenReturn(TestDataProvider.mockCompany());
        when(companyService.addCompany(any(CompanyDto.class))).thenReturn(TestDataProvider.mockCompanyList().get(0));
        when(companyService.deleteCompany(eq("xxx1"))).thenReturn(1L);
    }

    @Test
    void getAllCompany() throws Exception {
        mockMvc.perform(get("/v1.0/market/company/getall")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("xxx1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].code").value("xxx2"));
    }

    @Test
    void companyInfo() throws Exception {
        mockMvc.perform(get("/v1.0/market/company/info/{companyCode}", "xxx1")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("xxx1"));
    }

    @Test
    void registerCompany() throws Exception {
        mockMvc.perform(post("/v1.0/market/company/register")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(TestDataProvider.mockCompanyList().get(0)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("xxx1"));
    }

    @Test
    void deleteCompany() throws Exception {
        mockMvc.perform(delete("/v1.0/market/delete/{companyCode}", "xxx1")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("1")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}