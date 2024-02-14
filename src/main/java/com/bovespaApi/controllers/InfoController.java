package com.bovespaApi.controllers;

import com.bovespaApi.services.InfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.bovespaApi.utils.ReduzirCodigo.log;

@RestController
@RequestMapping("/api")
@Tag(name = "Informações", description = "Responsável por fornecer informações gerais")
public class InfoController {


    // Injeção de dependência do serviço de Informações
    private final InfoService infoService;

    @Autowired
    // Construtor com a injeção de dependência do serviço de Informações
    public InfoController(InfoService InfoService) {
        this.infoService = InfoService;
    }

    @Operation(summary = "JSON com todos os indicadores e suas chaves", description = "")
    @GetMapping("/info/indicadores")
    public List<Map<String, String>> getIndicadoresdaBolsa() {
        log.info("chamou o endpoint /info/indicadores");
        return infoService.getIndicadores();
    }

    @Operation(summary = "JSON com todos os setores e suas chaves", description = "")
    @GetMapping("/info/setores")
    public List<Map<String, String>> getSetoresDaBolsa() {
        log.info("chamou o endpoint /info/setores");
        return infoService.getSetores();
    }

}
