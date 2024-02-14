package com.bovespaApi.controllers;

import com.bovespaApi.services.BovespaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.List;
import java.util.Map;

import static com.bovespaApi.utils.ReduzirCodigo.log;

@RestController
@RequestMapping("/api")
@Tag(name = "Bovespa em geral", description = "Retorna informações sem filtros")
public class BovespaController {


    // Injeção de dependência do serviço de ações
    private final BovespaService acoesService;

    @Autowired
    // Construtor com a injeção de dependência do serviço de ações
    public BovespaController(BovespaService acoesService) {
        this.acoesService = acoesService;
    }


    @Operation(summary = "Lista com todos os papeis", description = "")
    @GetMapping("/bovespa/papeis")
        public List<String> getPapeis() throws IOException {
        log.info("chamou o endpoint /bovespa/papeis");
        return acoesService.getPapeis();
    }

    @Operation(summary = "JSON com todos os papeis e indicadores", description = "")
    @GetMapping("/bovespa/indicadores")
    public  List<Map<String, String>> getPapeisEstatisticas() throws IOException {
        log.info("chamou o endpoint /bovespa/indicadores");
        return acoesService.getPapeisComIndicadores();
    }


    @Operation(summary = "Lista com o nome de todas as empresas", description = "")
    @GetMapping("/bovespa/empresas")
    public List<String> getEmpresas() throws IOException {
        log.info("chamou o endpoint /bovespa/empresas");
        return acoesService.getEmpresas();
    }

}

