package br.com.fiap.smartcities.controller;

import br.com.fiap.smartcities.model.Accident;
import br.com.fiap.smartcities.service.AccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accidents")
public class AccidentController {

    @Autowired
    private AccidentService accidentService;

    @GetMapping
    public List<Accident> getAllAccidents() {
        return accidentService.getAllAccidents();
    }

    @PostMapping
    public Accident createAccident(@RequestBody Accident accident) {
        return accidentService.saveAccident(accident);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Accident> updateAccident(@PathVariable Long id, @RequestBody Accident accidentDetails) {
        Accident updatedAccident = accidentService.updateAccident(id, accidentDetails);
        return ResponseEntity.ok(updatedAccident);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccident(@PathVariable Long id) {
        accidentService.deleteAccident(id);
        return ResponseEntity.noContent().build();
    }
}
