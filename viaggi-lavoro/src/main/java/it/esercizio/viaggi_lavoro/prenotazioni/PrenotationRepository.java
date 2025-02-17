package it.esercizio.viaggi_lavoro.prenotazioni;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotationRepository extends JpaRepository<Prenotation, Long> {
}
