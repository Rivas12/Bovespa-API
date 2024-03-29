package com.bovespaApi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AcaoControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPapelEndpoint() throws Exception {
        String papel = "petr4"; // Defina um papel de exemplo para testar

        mockMvc.perform(get("/api/acao/{papel}", papel))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Adicione asserções adicionais conforme necessário
                .andExpect(jsonPath("$").isArray()); // Verifica se a resposta é uma lista JSON

        papel = "PAPEL_NAO_EXISTE4"; // Testa um papel que não existe
        mockMvc.perform(get("/api/acao/{papel}", papel))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetProventosEndpoint() throws Exception {
        String papel = "petr4"; // Defina um papel de exemplo para testar

        mockMvc.perform(get("/api/acao/{papel}/proventos", papel))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Adicione asserções adicionais conforme necessário
                .andExpect(jsonPath("$").isArray()); // Verifica se a resposta é uma lista JSON

        papel = "PAPEL_NAO_EXISTE4"; // Testa um papel que não existe
        mockMvc.perform(get("/api/acao/{papel}/proventos", papel))
                .andExpect(status().isNotFound());
    }
}
