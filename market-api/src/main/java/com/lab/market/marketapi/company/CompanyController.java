package com.lab.market.marketapi.company;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j(topic = "COMPANY_CONTROLLER")
@AllArgsConstructor
@RestController
@RequestMapping("/v1.0/market/company")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping(value = "/getall",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CompanyDto> getAllCompany() {
        return companyService.getAllCompanies();
    }

    @GetMapping(value = "/info/{companyCode}",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CompanyDto companyInfo(@PathVariable(value = "companyCode") String companyCode) {
        return companyService.getCompany(companyCode);
    }

    @PostMapping(value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public CompanyDto registerCompany(@Valid @RequestBody CompanyDto companyDto) {
        return companyService.addCompany(companyDto);
    }

    @DeleteMapping(value = "/delete/{companyCode}",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public long deleteCompany(@PathVariable(value = "companyCode") String companyCode) {
        return companyService.deleteCompany(companyCode);
    }

}
