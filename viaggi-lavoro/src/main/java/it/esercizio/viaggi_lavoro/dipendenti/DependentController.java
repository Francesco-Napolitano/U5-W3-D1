package it.esercizio.viaggi_lavoro.dipendenti;

import it.esercizio.viaggi_lavoro.exceptions.BadRequestException;
import it.esercizio.viaggi_lavoro.viaggi.Trip;
import it.esercizio.viaggi_lavoro.viaggi.TripRequest;
import it.esercizio.viaggi_lavoro.viaggi.TripResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dipendenti")
public class DependentController {
    @Autowired
    DependentService dependentService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public DependentResponse saveDependent(@RequestBody @Validated DependentRequest body, BindingResult validation) throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        Dependent dependent = dependentService.save(body);
        return new DependentResponse(dependent.getId());
    }

    @GetMapping("")
    public Page<Dependent> getDipendents(@RequestParam int page, @RequestParam int size, @RequestParam String sort) {
        return dependentService.getDipendents(page, size, sort);
    }

    @GetMapping("/{id}")
    public Dependent findById(@PathVariable Long id) {
        return dependentService.findById(id);
    }

    @PutMapping("/{id}")
    public Dependent findByIdAndUpdate(@PathVariable Long id, @RequestBody Dependent body) {
        return dependentService.findByIdAndUpdate(body, id);
    }

    //in grado di assegnare un dipendente ad un viaggio
    @PutMapping("/{id}/trips/{tripId}")
    public void assignDependentToTrip(@PathVariable Long id, @PathVariable Long tripId) {
        dependentService.assignDependentToTrip(id, tripId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long id) {
        dependentService.findByIdAndDelete(id);
    }


}