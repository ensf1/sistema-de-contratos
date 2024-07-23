package br.edu.ifal.contracts.controllers;


import br.edu.ifal.contracts.dtos.AuthDTO;
import br.edu.ifal.contracts.dtos.RegisterDTO;
import br.edu.ifal.contracts.models.AuthResponse;
import br.edu.ifal.contracts.models.User;
import br.edu.ifal.contracts.repositories.UserRepository;
import br.edu.ifal.contracts.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    private UserRepository repository;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterDTO registerDTO){

        if(this.repository.findByUsername(registerDTO.username()).isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(authService.register(new User(registerDTO.username(), registerDTO.password(), registerDTO.name(), registerDTO.email())));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthDTO authDTO){

        return ResponseEntity.ok(authService.authenticate(new User(authDTO.username(), authDTO.password())));
    }

}
