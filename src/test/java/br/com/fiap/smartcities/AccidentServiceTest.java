
package br.com.fiap.smartcities;

import br.com.fiap.smartcities.model.Accident;
import br.com.fiap.smartcities.model.Emergency;
import br.com.fiap.smartcities.repository.AccidentRepository;
import br.com.fiap.smartcities.service.AccidentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AccidentServiceTest {

    @Autowired
    private AccidentService accidentService;

    @MockBean
    private AccidentRepository accidentRepository;


    @Test
    public void testSaveEmergencyWithAccident() {
        Accident accident = new Accident();
        accident.setId(1L); // Define um ID para o Accident
        accident.setLocation("Localização Teste");

        Emergency emergency = new Emergency();
        emergency.setStatus("EM_ANDAMENTO");
        emergency.setAccident(accident); // Associa a emergência ao acidente

        when(accidentRepository.findById(1L)).thenReturn(Optional.of(accident)); // Simula a busca do Accident no banco
        when(accidentRepository.save(any(Accident.class))).thenReturn(accident); // Simula o salvamento do Accident

        accidentService.saveAccident(accident); // Salva o Accident (que já tem a Emergency associada)

        verify(accidentRepository, times(1)).save(accident); // Verifica se o Accident foi salvo
    }

    @Test
    public void testSaveAccident() {
        Accident accident = new Accident();
        accident.setLocation("Localização Teste");
        when(accidentRepository.save(any(Accident.class))).thenReturn(accident);

        Accident savedAccident = accidentService.saveAccident(accident);

        assertEquals("Localização Teste", savedAccident.getLocation());
        verify(accidentRepository, times(1)).save(accident);
    }

    @Test
    public void testUpdateAccident() {
        Accident existingAccident = new Accident();
        existingAccident.setId(1L);
        existingAccident.setLocation("Localização Antiga");
        when(accidentRepository.findById(1L)).thenReturn(Optional.of(existingAccident));

        Accident updatedAccident = new Accident();
        updatedAccident.setId(1L);
        updatedAccident.setLocation("Localização Nova");
        when(accidentRepository.save(any(Accident.class))).thenReturn(updatedAccident);

        Accident result = accidentService.updateAccident(1L, updatedAccident);

        assertEquals("Localização Nova", result.getLocation());
        verify(accidentRepository, times(1)).save(existingAccident);
    }

    @Test
    public void testDeleteAccident() {
        accidentService.deleteAccident(1L);
        verify(accidentRepository, times(1)).deleteById(1L);
    }
}
