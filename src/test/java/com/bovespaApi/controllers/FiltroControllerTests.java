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
public class FiltroControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetFiltrosEndpoint() throws Exception {
        mockMvc.perform(get("/api/filtro")
                        .param("pvp_max", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetPesquisaEndpoint() throws Exception {
        String termo = "petrobras";
        mockMvc.perform(get("/api/filtro/pesquisa/{termo}", termo))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
