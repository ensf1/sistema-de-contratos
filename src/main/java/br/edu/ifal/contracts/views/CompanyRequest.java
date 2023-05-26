package br.edu.ifal.contracts.views;

import br.edu.ifal.contracts.models.Company;

public record  CompanyRequest (String name, String address, String cnpj) {
    public Company mapToCompany(){
        return new Company(this.name(),this.address(), this.cnpj());
    }
}
