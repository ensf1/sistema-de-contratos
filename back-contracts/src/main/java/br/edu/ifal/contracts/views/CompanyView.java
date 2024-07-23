package br.edu.ifal.contracts.views;

import br.edu.ifal.contracts.models.Company;

public record CompanyView(String name, String address, String cnpj) {

    public CompanyView (Company company) {
        this(company.getName(), company.getAddress(), company.getCnpj());
    }
}
