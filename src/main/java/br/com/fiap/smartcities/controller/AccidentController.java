package br.com.fiap.smartcities.controller;

import br.com.fiap.smartcities.model.Accident;
import br.com.fiap.smartcities.service.AccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/accidents")
@Validated
public class AccidentController {

    @Autowired
    private AccidentService accidentService;


    @GetMapping
    public List<Accident> getAllAccidents() {
        return accidentService.getAllAccidents();
    }

    @GetMapping("/{id}")
    public Accident getAccidentById(@PathVariable Long id) {
        return accidentService.getAccidentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Acidente não encontrado"));
    }

    @PostMapping
    public ResponseEntity<Accident> createAccident(@Valid @RequestBody Accident accident, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().toString());
        }
        Accident createdAccident = accidentService.saveAccident(accident);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccident);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Accident> updateAccident(@PathVariable Long id, @Valid @RequestBody Accident accidentDetails, BindingResult bindingResult) {
        // Verifica se o objeto está vazio
        if (accidentDetails.getLocation() == null && accidentDetails.getStatus() == null) {
            bindingResult.reject("corpoVazio", "O corpo da requisição não pode estar vazio");
        }

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().toString());
        }
        Accident updatedAccident = accidentService.updateAccident(id, accidentDetails);
        return ResponseEntity.ok(updatedAccident);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccident(@PathVariable Long id) {
        accidentService.deleteAccident(id);
        return ResponseEntity.noContent().build();
    }
}