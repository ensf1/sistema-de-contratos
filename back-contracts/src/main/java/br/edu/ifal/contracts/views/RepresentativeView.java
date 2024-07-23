package br.edu.ifal.contracts.views;

import br.edu.ifal.contracts.models.Representative;

public record RepresentativeView(String name, String email) {
    public RepresentativeView(Representative representative) {
        this(representative.getName(), representative.getEmail());
    }
}
