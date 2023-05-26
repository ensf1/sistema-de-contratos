package br.edu.ifal.contracts.models;


import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Contract {
    @Id
    private Long id;
    private String title;
    @ManyToOne
    private Company contractedCompany;
    @Embedded
    private Representative companyRepresentative;


    public Contract(String title, Company contractedCompany, Representative companyRepresentative) {
        this.title = title;
        this.contractedCompany = contractedCompany;
        this.companyRepresentative = companyRepresentative;
    }

    public Long getId() {
        return id;
    }

    protected Contract() { //JPA
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Company getContractedCompany() {
        return contractedCompany;
    }

    public void setContractedCompany(Company contractedCompany) {
        this.contractedCompany = contractedCompany;
    }

    public Representative getCompanyRepresentative() {
        return companyRepresentative;
    }

    public void setCompanyRepresentative(Representative companyRepresentative) {
        this.companyRepresentative = companyRepresentative;
    }

}