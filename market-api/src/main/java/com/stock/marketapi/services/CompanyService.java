package com.stock.marketapi.services;

import com.stock.marketapi.entity.CompanyEntity;
import com.stock.marketapi.mapper.CompanyMapper;
import com.stock.marketapi.model.Company;
import com.stock.marketapi.model.CompanyDto;
import com.stock.marketapi.exception.RecordNotFoundException;
import com.stock.marketapi.repository.CompanyRepository;
import com.stock.marketapi.model.StockDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;
    private final CompanyMapper companyMapper;
    private final StockService stockService;

    public List<CompanyDto> getAllCompanies() {
        return repository.findAll()
                .parallelStream()
                .map(companyMapper::map).collect(Collectors.toList());

    }

    public Company getCompany(String code) {
        CompanyEntity companyEntity = repository.findByCode(code).orElseThrow(() ->
                new RecordNotFoundException("Record Not found"));
        List<StockDto> stocks = stockService.companyInfo(code);
        Company company = companyMapper.map(companyEntity, stocks);
        DoubleSummaryStatistics stats = stocks.stream().map(StockDto::getPrice).mapToDouble(n -> n).summaryStatistics();
        company.setAvg(stats.getAverage());
        company.setMax(stats.getMax());
        company.setMin(stats.getMin());
        return company;
    }

    public CompanyDto addCompany(CompanyDto companyDto) {
        CompanyEntity companyEntity = repository.save(companyMapper.map(companyDto));
        return companyMapper.map(companyEntity);
    }

    public long deleteCompany(String code) {
        return repository.findByCode(code)
                .map(companyEntity -> stockService.deleteStock(code))
                .map(entity -> repository.deleteByCode(code))
                .orElseThrow(() ->
                        new RecordNotFoundException("Record Not found"));
    }
}
