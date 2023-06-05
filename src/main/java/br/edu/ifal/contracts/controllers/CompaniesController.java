package br.edu.ifal.contracts.controllers;

import br.edu.ifal.contracts.dtos.CompanyDto;
import br.edu.ifal.contracts.repositories.CompaniesRepository;
import br.edu.ifal.contracts.views.CompanyView;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/v1/companies")
public class CompaniesController {
    private final CompaniesRepository companiesRepository;

    public CompaniesController(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }
    @PostMapping
    public ResponseEntity<Object> addCompany(@Valid @RequestBody CompanyDto companyDto) {
        this.companiesRepository.save(companyDto.mapToCompany());
        return ResponseEntity.status(201).build();
    }
    @GetMapping
    public List<CompanyView> getCompanies(){
        return this.companiesRepository.findAll().stream().map(CompanyView::new).toList();
    }
}
