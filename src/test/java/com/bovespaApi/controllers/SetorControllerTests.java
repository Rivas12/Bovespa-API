package com.bovespaApi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Optional.empty;
import static java.util.function.Predicate.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class SetorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPapeisPorSetor() throws Exception{
        String setor = "1";
        mockMvc.perform(get("/api/setor/{setor}/papeis", setor))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testGetIndicadoresPorSetor() throws Exception{
        String setor = "1";
        mockMvc.perform(get("/api/setor/{setor}/indicadores", setor))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testGetEmpresasPorSetor() throws Exception{
        String setor = "1";
        mockMvc.perform(get("/api/setor/{setor}/empresas", setor))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty());
    }

}
