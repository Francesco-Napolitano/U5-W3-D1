package it.esercizio.viaggi_lavoro.viaggi;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TripRequest {
    @NotEmpty(message = "Destinazione non pu  essere vuota")
    private String destinazione;
    @NotNull(message = "Data di partenza non pu  essere vuota")
    private LocalDate dataPartenza;
}
