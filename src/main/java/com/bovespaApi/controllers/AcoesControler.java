package com.bovespaApi.controllers;

import com.bovespaApi.services.AcoesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/acoes")
@Tag(name = "Ações Controller")
public class AcoesControler {

    private final AcoesService acoesService;

    @Autowired
    public AcoesControler(AcoesService acoesService) {
        this.acoesService = acoesService;
    }

    @GetMapping("/papeis")
    public List<String> getPapeis() throws IOException {
        return acoesService.getPapeis();
    }

    @GetMapping("/papeis/estatisticas")
    public  List<Map<String, String>> getPapeisEstatisticas() throws IOException {
        return acoesService.getPapeisEstatisticas();
    }
    @GetMapping("/papeis/ordernar/{chave}")
    public List<Map<String, String>> getPapelPorOrdem(@PathVariable String chave) throws IOException {
        return acoesService.getPapelPorOrdem(chave);
    }

    @GetMapping("/papeis/setor/{setor}")
    public List<String> getPapeisPorSetor(@PathVariable String setor) throws IOException {
        return acoesService.getPapeisPorSetor(setor);
    }

    @GetMapping("/papeis/setor/{setor}/estatisticas")
    public List<Map<String, String>> getPapeisPorSetorEstatisticas(@PathVariable String setor) throws IOException {
        return acoesService.getPapeisPorSetorEstatisticas(setor);
    }

    @GetMapping("/papel/{papel}/estatisticas")
    public List<Map<String, String>> getPapelEstatisticas(@PathVariable String papel) throws IOException {
        return acoesService.getPapelEstatisticas(papel.toUpperCase());
    }


}

