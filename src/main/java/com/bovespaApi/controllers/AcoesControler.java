package com.bovespaApi.controllers;

import com.bovespaApi.services.AcoesService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/acoes")
@Tag(name = "Ações Controller")
public class AcoesControler {

    // Injeção de dependência do serviço de ações
    private final AcoesService acoesService;

    @Autowired
    // Construtor com a injeção de dependência do serviço de ações
    public AcoesControler(AcoesService acoesService) {
        this.acoesService = acoesService;
    }

    // Esses métodos retornam listas que contem o nome dos papeis ========================

    // Método para retornar os papeis [LISTA DE PAPEIS]
    @GetMapping("/papeis/lista")
    public List<String> getPapeis() throws IOException {
        return acoesService.getPapeis();
    }

    // Método para retornar os papeis que contém o termo pesquisado [LISTA DE PAPEIS]
    @GetMapping("/papeis/setor/{setor}")
    public List<String> getPapeisPorSetor(@PathVariable String setor) throws IOException {
        return acoesService.getPapeisPorSetor(setor);
    }

    // ===================================================================================


    // Esse método retorna os papeis que contém o termo pesquisado
    @GetMapping("/papeis/indicadores")
    public  List<Map<String, String>> getPapeisEstatisticas() throws IOException {
        return acoesService.getPapeisEstatisticas();
    }

    // Método para retornar os papeis que contém o termo pesquisado
    @GetMapping("/papeis/ordernar/{chave}")
    public List<Map<String, String>> getPapelPorOrdem(@PathVariable String chave) throws IOException {
        return acoesService.getPapelPorOrdem(chave);
    }

    // Método para retornar os papeis que contém o termo pesquisado
    @GetMapping("/papeis/setor/{setor}/indicadores")
    public List<Map<String, String>> getPapeisPorSetorEstatisticas(@PathVariable String setor) throws IOException {
        return acoesService.getPapeisPorSetorEstatisticas(setor);
    }

    // Método para retornar os papeis que contém o termo pesquisado
    @GetMapping("/papeis/pesquisa/{termo}")
    public  List<Map<String, String>> getPapeisPesquisa(@PathVariable String termo) throws IOException {
        return acoesService.getPapeisPesquisa(termo);
    }

    // Método para retornar as estatísticas de um único papel
    @GetMapping("/papel/{papel}/indicadores")
    public List<Map<String, String>> getPapelEstatisticas(@PathVariable String papel) throws IOException {
        return acoesService.getPapelEstatisticas(papel.toUpperCase());
    }

}

