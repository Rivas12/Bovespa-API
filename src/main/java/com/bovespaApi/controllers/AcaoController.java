package com.bovespaApi.controllers;


import com.bovespaApi.services.AcaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.bovespaApi.utils.ReduzirCodigo.log;

@RestController
@RequestMapping("/api")
@Tag(name = "Ação", description = "Retorna informações de uma única ação")
public class AcaoController {

    // Injeção de dependência do serviço de ações
    private final AcaoService acaoService;

    @Autowired
    // Construtor com a injeção de dependência do serviço de ações
    public AcaoController(AcaoService acaoService) {
        this.acaoService = acaoService;
    }

    @GetMapping("/acao/{papel}")
    @Operation(summary = "JSON com indicadores do papel", description = "")
    public List<Map<String, String>> getPapel(@PathVariable String papel) throws IOException {
        log.info("chamou o endpoint /acao/{papel}");
        return acaoService.getPapel(papel);
    }

    @GetMapping("/acao/{papel}/proventos")
    @Operation(summary = "JSON todos os proventos registrados. Dividendos, Juro sobre capital, etc...", description = "")
    public List<Map<String, String>> getProventos(@PathVariable String papel) throws IOException {
        log.info("chamou o endpoint /acao/{papel}/proventos");
        return acaoService.getProventos(papel);
    }
}
