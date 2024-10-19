package br.com.fiap.smartcities;

import br.com.fiap.smartcities.controller.AccidentController;
import br.com.fiap.smartcities.model.Accident;
import br.com.fiap.smartcities.service.AccidentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccidentController.class)
public class AccidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccidentService accidentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    public void testGetAllAccidents() throws Exception {
        List<Accident> accidents = Arrays.asList(new Accident(), new Accident());
        when(accidentService.getAllAccidents()).thenReturn(accidents);

        mockMvc.perform(get("/api/accidents")
                        .with(httpBasic("user", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @WithMockUser
    public void testCreateAccident() throws Exception {
        Accident accident = new Accident();
        accident.setLocation("Localização Teste");
        when(accidentService.saveAccident(any(Accident.class))).thenReturn(accident);

        mockMvc.perform(post("/api/accidents")
                        .with(httpBasic("user", "password"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(accident)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("Localização Teste"));
    }

    @Test
    @WithMockUser
    public void testUpdateAccident() throws Exception {
        Accident accident = new Accident();
        accident.setId(1L);
        accident.setLocation("Localização Teste");
        when(accidentService.updateAccident(any(Long.class), any(Accident.class))).thenReturn(accident);

        mockMvc.perform(put("/api/accidents/1")
                        .with(httpBasic("user", "password"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(accident)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("Localização Teste"));
    }

    @Test
    @WithMockUser
    public void testDeleteAccident() throws Exception {
        mockMvc.perform(delete("/api/accidents/1")
                        .with(httpBasic("user", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    public String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}