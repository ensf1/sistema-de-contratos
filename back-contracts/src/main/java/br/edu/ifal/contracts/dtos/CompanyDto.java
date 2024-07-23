package br.edu.ifal.contracts.dtos;

import br.edu.ifal.contracts.models.Company;
import jakarta.validation.constraints.NotEmpty;

public class CompanyDto {
    @NotEmpty
    private final String name;
    private final String address;
    private final String cnpj;

    public CompanyDto(String name, String address, String cnpj) {
        this.name = name;
        this.address = address;
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Company mapToCompany(){
        return new Company(this.getName(),this.getAddress(), this.getCnpj());
    }
}
