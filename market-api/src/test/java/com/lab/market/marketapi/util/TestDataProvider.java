package com.lab.market.marketapi.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.market.marketapi.model.Company;
import com.lab.market.marketapi.model.CompanyDto;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TestDataProvider {

    static ObjectMapper mapper = new ObjectMapper();

    public static List<CompanyDto> mockCompanyList() {
        try {
            return mapper.readValue(new File("src/test/resources/companyList.json"),
                    new TypeReference<List<CompanyDto>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static Company mockCompany() {
        try {
            return mapper.readValue(new File("src/test/resources/company.json"),
                    Company.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Company();
    }
}
