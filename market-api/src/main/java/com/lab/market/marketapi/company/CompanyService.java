package com.lab.market.marketapi.company;

import com.lab.market.marketapi.exception.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;
    private final CompanyMapper companyMapper;

    public List<CompanyDto> getAllCompanies() {
        return repository.findAll()
                .parallelStream()
                .map(companyMapper::map).collect(Collectors.toList());

    }

    public CompanyDto getCompany(String code) {
        CompanyEntity companyEntity = repository.findByCode(code).orElseThrow(() ->
                new RecordNotFoundException("Record Not found"));
        return companyMapper.map(companyEntity);
    }

    public CompanyDto addCompany(CompanyDto companyDto) {
        CompanyEntity companyEntity = repository.save(companyMapper.map(companyDto));
        return companyMapper.map(companyEntity);
    }

    public long deleteCompany(String code) {
        return repository.findByCode(code).map(companyEntity1 -> repository.deleteByCode(code))
                .orElseThrow(() ->
                        new RecordNotFoundException("Record Not found"));
    }

}
