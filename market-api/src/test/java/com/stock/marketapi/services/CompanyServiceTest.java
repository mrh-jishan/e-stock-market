package com.stock.marketapi.services;

import com.stock.marketapi.entity.CompanyEntity;
import com.stock.marketapi.mapper.CompanyMapper;
import com.stock.marketapi.model.Company;
import com.stock.marketapi.model.CompanyDto;
import com.stock.marketapi.repository.CompanyRepository;
import com.stock.marketapi.util.TestDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository repository;

    @Spy
    private CompanyMapper companyMapper;

    @Mock
    private StockService stockService;

    @Test
    void getAllCompanies() {
        when(repository.findAll()).thenReturn(TestDataProvider.mockCompanyEntity());
        List<CompanyDto> allCompanies = companyService.getAllCompanies();
        assertNotNull(allCompanies);
        assertEquals(3, allCompanies.size());
        verify(companyMapper, times(3)).map(any(CompanyEntity.class));
    }

    @Test
    void getCompany() {
        when(repository.findByCode(eq("xxx"))).thenReturn(Optional.of(TestDataProvider.mockCompanyEntity().get(0)));
        when(stockService.companyInfo(eq("xxx"))).thenReturn(TestDataProvider.mockStockList());
        when(companyMapper.map(any(CompanyEntity.class), anyList())).thenReturn(TestDataProvider.mockCompany());
        Company company = companyService.getCompany("xxx");
        assertNotNull(company);
        verify(companyMapper, times(1)).map(any(CompanyEntity.class), anyList());
    }
}