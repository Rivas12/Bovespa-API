package com.bovespaApi.controllers;

import com.bovespaApi.services.BovespaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.List;
import java.util.Map;

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
        return acoesService.getPapeis();
    }


    @Operation(summary = "JSON com todos os papeis e indicadores", description = "")
    @GetMapping("/bovespa/indicadores")
    public  List<Map<String, String>> getPapeisEstatisticas() throws IOException {
        return acoesService.getPapeisComIndicadores();
    }

    @Operation(summary = "JSON com todos os papeis por ordem do indicador desejado", description = "")
    @GetMapping("/papeis/ordernar_por/{chave}/{cresc_desc}")
    public List<Map<String, String>> getPapelPorOrdem(@PathVariable String chave,@PathVariable String cresc_desc) throws IOException {
        return acoesService.getPapelPorOrdem(chave, cresc_desc);
    }


    @Operation(summary = "JSON com todos os papeis que contém o termo pesquisado", description = "")
    @GetMapping("/papeis/pesquisa/{termo}")
    public  List<Map<String, String>> getPapeisPesquisa(@PathVariable String termo) throws IOException {
        return acoesService.getPapeisPesquisa(termo);
    }

    @Operation(summary = "JSON com um único papel e indicadores", description = "")
    @GetMapping("/papel/{papel}")
    public List<Map<String, String>> getPapelEstatisticas(@PathVariable String papel) throws IOException {
        return acoesService.getPapelComIndicadores(papel.toUpperCase());
    }

    @Operation(summary = "Lista com o nome de todas as empresas", description = "")
    @GetMapping("/bovespa/empresas")
    public List<String> getEmpresas() throws IOException {
        return acoesService.getEmpresas();
    }

}

