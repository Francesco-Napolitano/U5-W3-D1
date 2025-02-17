package it.esercizio.viaggi_lavoro.viaggi;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@RequiredArgsConstructor
@Validated
public class TripService {

    @Autowired
    private final TripRepository tripRepository;


    //operazione CREATE
    public Trip save(TripRequest body) {
        Trip trip = new Trip();
        trip.setDestinazione(body.getDestinazione());
        trip.setDataPartenza(body.getDataPartenza());
        trip.setStato(Stato.IN_PROGRAMMA);
        return tripRepository.save(trip);
    }

    //operazione GET con la paginazione
    public Page<Trip> getTrips(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return tripRepository.findAll(pageable);
    }

    //operazione PUT
    public Trip findByIdAndUpdate(Trip body, Long id) {
        Trip trip = tripRepository.findById(id).orElse(null);
        trip.setDestinazione(body.getDestinazione());
        trip.setDataPartenza(body.getDataPartenza());
        trip.setStato(Stato.IN_PROGRAMMA);
        return tripRepository.save(trip);
    }

    //operazione GET id
    public Trip findById(Long id) {
        return tripRepository.findById(id).orElse(null);
    }

    //operazione DELETE
    public void findByIdAndDelete(Long id) {
        tripRepository.deleteById(id);
    }

    //operazione UPDATE stato

    public Trip updateStato(Long id, Stato stato) {
        Trip trip = tripRepository.findById(id).orElseThrow();
        trip.setStato(stato);
        return tripRepository.save(trip);
    }


}
