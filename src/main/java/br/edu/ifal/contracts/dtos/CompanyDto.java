package br.edu.ifal.contracts.dtos;

import br.edu.ifal.contracts.models.Company;

public record CompanyDto(String name, String address, String cnpj) {
    public Company mapToCompany(){
        return new Company(this.name(),this.address(), this.cnpj());
    }
}
