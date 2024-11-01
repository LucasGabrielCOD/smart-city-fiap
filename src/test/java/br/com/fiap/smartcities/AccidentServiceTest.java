package br.com.fiap.smartcities;

import br.com.fiap.smartcities.model.Accident;
import br.com.fiap.smartcities.repository.AccidentRepository;
import br.com.fiap.smartcities.service.AccidentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AccidentServiceTest {

    @Autowired
    private AccidentService accidentService;

    @MockBean
    private AccidentRepository accidentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private JsonSchema saveAccidentSchema;
    private JsonSchema updateAccidentSchema;

    @BeforeEach
    public void setUp() throws IOException, ProcessingException {
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

        // Define schemas para cada operação
        saveAccidentSchema = factory.getJsonSchema(objectMapper.readTree("{\n" +
                "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"properties\": {\n" +
                "    \"id\": {\"type\": \"integer\"},\n" +
                "    \"location\": {\"type\": \"string\"}\n" +
                "  },\n" +
                "  \"required\": [\"id\", \"location\"]\n" +
                "}"));

        updateAccidentSchema = factory.getJsonSchema(objectMapper.readTree("{\n" +
                "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"properties\": {\n" +
                "    \"id\": {\"type\": \"integer\"},\n" +
                "    \"location\": {\"type\": \"string\"}\n" +
                "  },\n" +
                "  \"required\": [\"id\", \"location\"]\n" +
                "}"));
    }

    private void validateJsonSchema(Object responseObject, JsonSchema schema) throws IOException, ProcessingException {
        String jsonResponse = objectMapper.writeValueAsString(responseObject);
        ProcessingReport report = schema.validate(objectMapper.readTree(jsonResponse));
        assertTrue(report.isSuccess(), "A resposta JSON não corresponde ao schema esperado: " + report.toString());
    }

    @Test
    public void testSaveAccident() throws IOException, ProcessingException {
        Accident accident = new Accident();
        accident.setId(1L);
        accident.setLocation("Localização Teste");

        when(accidentRepository.save(any(Accident.class))).thenReturn(accident);

        Accident savedAccident = accidentService.saveAccident(accident);

        // Valida o corpo da resposta e o schema
        validateJsonSchema(savedAccident, saveAccidentSchema);

        verify(accidentRepository, times(1)).save(accident);
    }

    @Test
    public void testUpdateAccident() throws IOException, ProcessingException {
        Accident existingAccident = new Accident();
        existingAccident.setId(1L);
        existingAccident.setLocation("Localização Antiga");

        when(accidentRepository.findById(1L)).thenReturn(Optional.of(existingAccident));

        Accident updatedAccident = new Accident();
        updatedAccident.setId(1L);
        updatedAccident.setLocation("Localização Nova");

        when(accidentRepository.save(any(Accident.class))).thenReturn(updatedAccident);

        Accident result = accidentService.updateAccident(1L, updatedAccident);

        // Valida o corpo da resposta e o schema
        validateJsonSchema(result, updateAccidentSchema);

        verify(accidentRepository, times(1)).save(existingAccident);
    }

    @Test
    public void testDeleteAccident() {
        accidentService.deleteAccident(1L);
        verify(accidentRepository, times(1)).deleteById(1L);
    }
}
