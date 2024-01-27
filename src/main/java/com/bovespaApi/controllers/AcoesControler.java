package com.bovespaApi.controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/acoes")
public class AcoesControler {
    @GetMapping("/papeis")
    public List<String> papeis() throws IOException {
        // URL do site da tabela
        String url = "https://www.fundamentus.com.br/resultado.php";

        // Conectar ao site e obter o HTML
        Document document = Jsoup.connect(url).get();

        // Selecionar a tabela
        Element table = document.select("table").first();

        // Criar uma lista para armazenar os dados da tabela
        List<String> jsonData = new ArrayList<>();

        // Verificar se a tabela foi encontrada
        if (table != null) {
            // Obter todas as linhas da tabela
            Elements linhas_table = table.select("tbody tr");

            // Itere sobre as linhas da tabela
            for (Element linha : linhas_table) {
                // Obtenha a célula da linha
                Elements celula = linha.select("td");

                // Retira o texto do HTML e adiciona o papel à lista
                jsonData.add(celula.get(0).text());
            }
        }
        // Retornar lista em
        return jsonData;
    }
}
