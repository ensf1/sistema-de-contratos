package br.edu.ifal.contracts.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Representative {
    private String name;
    private String email;

    public Representative(String name, String email) {
        this.name = name;
        this.email = email;
    }

    protected Representative() { //JPA
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
