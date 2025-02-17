package it.esercizio.viaggi_lavoro.dipendenti;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class DependentRequest {
    @NotEmpty
    @Size(min = 3, max = 16)
    private String username;
    @NotEmpty
    @Size(min = 8, max = 32)
    private String password;
    @NotEmpty
    private String nome;
    @NotEmpty
    private String cognome;
    @Email
    @NotEmpty
    private String email;
}
