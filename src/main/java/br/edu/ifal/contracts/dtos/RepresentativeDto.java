package br.edu.ifal.contracts.dtos;

import br.edu.ifal.contracts.models.Representative;

public record RepresentativeDto(String name, String email) {
    public Representative mapToRepresentative(){
        return new Representative(this.name(), this.email());
    }
}
