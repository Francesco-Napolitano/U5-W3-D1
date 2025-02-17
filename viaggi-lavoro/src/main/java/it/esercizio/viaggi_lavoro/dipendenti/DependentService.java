package it.esercizio.viaggi_lavoro.dipendenti;

import it.esercizio.viaggi_lavoro.prenotazioni.Prenotation;
import it.esercizio.viaggi_lavoro.prenotazioni.PrenotationRepository;
import it.esercizio.viaggi_lavoro.viaggi.Trip;
import it.esercizio.viaggi_lavoro.viaggi.TripRepository;
import it.esercizio.viaggi_lavoro.viaggi.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Validated
public class DependentService {
    @Autowired
    private final DependentRepository dependentRepository;

    @Autowired
    private final TripRepository tripRepository;

    @Autowired
    private final PrenotationRepository prenotationRepository;

    //operazione GET ALL
    public Page<Dependent> getDipendents(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return dependentRepository.findAll(pageable);
    }

    //operazione get ID
    public Dependent findById(Long id) {
        return dependentRepository.findById(id).orElse(null);
    }

    //operazione put
    public Dependent findByIdAndUpdate(Dependent body, Long id) {
        Dependent dependent = dependentRepository.findById(id).orElse(null);
        dependent.setUsername(body.getUsername());
        dependent.setNome(body.getNome());
        dependent.setCognome(body.getCognome());
        dependent.setEmail(body.getEmail());
        return dependentRepository.save(dependent);
    }

    public void assignDependentToTrip(Long dependentId, Long tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow();

        Prenotation prenotation = new Prenotation();
        prenotation.setNote("");
        prenotation.setData_richiesta(LocalDate.now());
        prenotation.setTrip(trip);
        prenotationRepository.save(prenotation);
    }


    //operazione DELETE by id
    public void findByIdAndDelete(Long id) {
        dependentRepository.deleteById(id);
    }

    //operazione CREATE
    public Dependent save(DependentRequest body) {
        Dependent dependent = new Dependent();
        dependent.setUsername(body.getUsername());
        dependent.setNome(body.getNome());
        dependent.setCognome(body.getCognome());
        dependent.setEmail(body.getEmail());
        return dependentRepository.save(dependent);
    }
}
