package br.edu.ifal.contracts.models;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public abstract class Contract implements Payable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String number;
    @Enumerated(EnumType.STRING)
    private ContractType type;
    private LocalDate start;
    @Column(name = "_end")
    private LocalDate end;
    @ManyToOne
    private Company contractedCompany;
    @Embedded
    private Representative companyRepresentative;


    public Contract(String title, String number, Company contractedCompany, Representative companyRepresentative, LocalDate start, LocalDate end) {
        this.title = title;
        this.number = number;
        this.contractedCompany = contractedCompany;
        this.companyRepresentative = companyRepresentative;
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    protected Contract() { //JPA
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ContractType getType() {
        return type;
    }

    public void setType(ContractType type) {
        this.type = type;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
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

    @Override
    public abstract LocalDate nextPayment();
}
