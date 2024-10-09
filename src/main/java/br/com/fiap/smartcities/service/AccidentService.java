package br.com.fiap.smartcities.service;

import br.com.fiap.smartcities.model.Accident;
import br.com.fiap.smartcities.repository.AccidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccidentService {

    @Autowired
    private AccidentRepository accidentRepository;

    public List<Accident> getAllAccidents() {
        return accidentRepository.findAll();
    }

    public Accident saveAccident(Accident accident) {
        return accidentRepository.save(accident);
    }

    public Accident updateAccident(Long id, Accident accidentDetails) {
        Accident accident = accidentRepository.findById(id).orElseThrow();
        accident.setLocation(accidentDetails.getLocation());
        accident.setStatus(accidentDetails.getStatus());
        return accidentRepository.save(accident);
    }

    public void deleteAccident(Long id)

    {
        accidentRepository.deleteById(id);
    }
}
