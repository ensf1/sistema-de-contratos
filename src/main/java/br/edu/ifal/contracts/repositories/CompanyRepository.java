package br.edu.ifal.contracts.repositories;

import br.edu.ifal.contracts.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
