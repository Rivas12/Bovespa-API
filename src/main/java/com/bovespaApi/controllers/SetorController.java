package com.bovespaApi.controllers;


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

import com.bovespaApi.services.SetorService;

@RestController
@RequestMapping("/api")
@Tag(name = "Setores", description = "Retorna informações baseado nos setores")
public class SetorController {

        // Injeção de dependência do serviço de ações
        private final SetorService setorService;

        @Autowired
        // Construtor com a injeção de dependência do serviço de ações
        public SetorController(SetorService setorService) {
            this.setorService = setorService;
        }


        @Operation(summary = "Lista com todos os papeis", description = "")
        @GetMapping("/setor/{setor}/papeis")
        public List<String> getPapeisPorSetor(@PathVariable String setor) throws IOException {
            return setorService.getPapeis(setor);
        }

        @Operation(summary = "JSON com todos os papeis e indicadores", description = "")
        @GetMapping("/setor/{setor}/indicadores")
        public List<Map<String, String>> getPapeisPorSetorEstatisticas(@PathVariable String setor) throws IOException {
            return setorService.getIndicadores(setor);
        }

        @Operation(summary = "Lista com o nome de todas as empresas por setor", description = "")
        @GetMapping("/setor/{setor}/empresas")
        public List<String> getEmpresasPorSetor(@PathVariable String setor) throws IOException {
            return setorService.getEmpresas(setor);
        }

        @Operation(summary = "JSON com todos os setores e suas chaves", description = "")
        @GetMapping("/setores")
        public List<Map<String, String>> getSetoresDaBolsa() throws IOException {
                return setorService.getSetores();
        }

}
