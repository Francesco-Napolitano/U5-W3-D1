package it.esercizio.viaggi_lavoro.viaggi;

import it.esercizio.viaggi_lavoro.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viaggi")
public class TripController {
    @Autowired
    TripService tripService;

    @Autowired
    TripRepository tripRepository;

    //operazione POST
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public TripResponse saveTrip(@RequestBody @Validated TripRequest body, BindingResult validation) throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        Trip trip = tripService.save(body);
        return new TripResponse(trip.getId());
    }

    //operazione GET con la paginazione
    @GetMapping("")
    public Page<Trip> getTrips(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy) {
        return tripService.getTrips(page, size, sortBy);
    }

    //operazione GET con id
    @GetMapping("/{id}")
    public Trip findById(@PathVariable Long id) {
        return tripService.findById(id);
    }

    //operazione PUT
    @PutMapping("/{id}")
    public Trip findByIdAndUpdate(@PathVariable Long id, @RequestBody Trip body) {
        return tripService.findByIdAndUpdate(body, id);

    }

    //operazione DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long id) {
        tripService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}/stato")
    public Trip updateStato(@PathVariable Long id, @RequestBody Stato stato) {
        Trip trip = tripRepository.findById(id).orElseThrow();
        trip.setStato(stato);
        return tripRepository.save(trip);

    }

}
