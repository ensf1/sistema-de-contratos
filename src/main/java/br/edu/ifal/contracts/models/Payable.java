package br.edu.ifal.contracts.models;

import java.time.LocalDate;

public interface Payable {
    LocalDate nextPayment();
}
