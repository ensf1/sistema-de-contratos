package br.edu.ifal.contracts.views;

import br.edu.ifal.contracts.models.Contract;

public record ContractView(String title, CompanyView contractedCompany, RepresentativeView companyRepresentative) {
    public ContractView(Contract contract) {
        this(contract.getTitle(), new CompanyView(contract.getContractedCompany()), new RepresentativeView(contract.getCompanyRepresentative()));
    }
}
