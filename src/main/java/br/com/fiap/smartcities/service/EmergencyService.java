package br.com.fiap.smartcities.service;

import br.com.fiap.smartcities.model.Emergency;
import br.com.fiap.smartcities.repository.EmergencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyService {

    @Autowired
    private EmergencyRepository emergencyRepository;

    public List<Emergency> getAllEmergencies() {
        return emergencyRepository.findAll();
    }

    public Emergency saveEmergency(Emergency emergency) {
        return emergencyRepository.save(emergency);
    }

    public Emergency updateEmergency(Long id, Emergency emergencyDetails) {
        Emergency emergency = emergencyRepository.findById(id).orElseThrow();
        emergency.setStatus(emergencyDetails.getStatus());
        return emergencyRepository.save(emergency);
    }

    public void deleteEmergency(Long id) {
        emergencyRepository.deleteById(id);
    }
}

