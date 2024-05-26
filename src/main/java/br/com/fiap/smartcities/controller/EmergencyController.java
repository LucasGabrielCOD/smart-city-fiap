package br.com.fiap.smartcities.controller;

import br.com.fiap.smartcities.model.Emergency;
import br.com.fiap.smartcities.service.EmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergencies")
public class EmergencyController {

    @Autowired
    private EmergencyService emergencyService;

    @GetMapping
    public List<Emergency> getAllEmergencies() {
        return emergencyService.getAllEmergencies();
    }

    @PostMapping
    public Emergency createEmergency(@RequestBody Emergency emergency) {
        return emergencyService.saveEmergency(emergency);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emergency> updateEmergency(@PathVariable Long id, @RequestBody Emergency emergencyDetails) {
        Emergency updatedEmergency = emergencyService.updateEmergency(id, emergencyDetails);
        return ResponseEntity.ok(updatedEmergency);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmergency(@PathVariable Long id) {
        emergencyService.deleteEmergency(id);
        return ResponseEntity.noContent().build();
    }
}