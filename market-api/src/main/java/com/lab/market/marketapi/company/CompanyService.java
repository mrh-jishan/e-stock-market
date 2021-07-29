package com.lab.market.marketapi.company;

import com.lab.market.marketapi.exception.RecordNotFoundException;
import com.lab.market.marketapi.stock.StockDto;
import com.lab.market.marketapi.stock.StockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        List<StockDto> stocks = stockService.companyInfo(code, new Date(), new Date());
        return companyMapper.map(companyEntity, stocks);
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
