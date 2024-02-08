package com.bovespaApi.controllers;


import com.bovespaApi.services.FiltroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
@Tag(name = "Filtros & Pesquisa", description = "Retorna informações com todos os filtros disponíveis.")
public class FiltroController {

    // Injeção de dependência do serviço de ações
    private final FiltroService filtroService;

    @Autowired
    // Construtor com a injeção de dependência do serviço de ações
    public FiltroController(FiltroService filtroService) {
        this.filtroService = filtroService;
    }


}
