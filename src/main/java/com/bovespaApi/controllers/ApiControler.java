package com.bovespaApi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiControler {
    @GetMapping("/primeiro-endpoint")
    public String primeiroEndpoint(){
        return "API Funcionando!";
    }
}
