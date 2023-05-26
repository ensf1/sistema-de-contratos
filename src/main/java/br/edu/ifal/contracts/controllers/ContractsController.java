package br.edu.ifal.contracts.controllers;

import br.edu.ifal.contracts.views.ContractRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/v1/contracts")
public class ContractsController {
    @GetMapping
    public List<Object> getContracts(){

        return List.of();
    }

    @PostMapping
    public ResponseEntity<String> addContract(@RequestBody ContractRequest contractRequest) throws URISyntaxException {
        return ResponseEntity.created(new URI("http://localhost:8080/v1/contracts")).body("");
    }
}