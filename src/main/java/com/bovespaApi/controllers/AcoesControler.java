package com.bovespaApi.controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/acoes")
public class AcoesControler {
    @GetMapping("/papeis")
    public List<String> getPapeis() throws IOException {
        // URL do site da tabela
        String url = "https://www.fundamentus.com.br/resultado.php";

        // Conectar ao site e obter o HTML
        Document document = Jsoup.connect(url).get();

        // Selecionar a tabela
        Element table = document.select("table").first();

        // Criar uma lista para armazenar os dados da tabela
        List<String> listData = new ArrayList<>();

        // Verificar se a tabela foi encontrada
        if (table != null) {
            // Obter todas as linhas da tabela
            Elements linhas_table = table.select("tbody tr");

            // Itere sobre as linhas da tabela
            for (Element linha : linhas_table) {
                // Obtenha a célula da linha
                Elements celula = linha.select("td");

                // Retira o texto do HTML e adiciona o papel à lista
                listData.add(celula.get(0).text());
            }
        }
        // Retornar lista
        return listData;
    }

    @GetMapping("/papeis/estatisticas")
    public  List<Map<String, String>> getPapeisEstatisticas() throws IOException {
        // URL do site da tabela
        String url = "https://www.fundamentus.com.br/resultado.php";

        // Conectar ao site e obter o HTML
        Document document = Jsoup.connect(url).get();

        // Selecionar a tabela
        Element table = document.select("table").first();

        // Criar uma lista para armazenar os dados da tabela
        List<Map<String, String>> jsonData = new ArrayList<>();

        // Verifique se a tabela foi encontrada
        if (table != null) {
            // Obter todas as linhas da tabela
            Elements linhas_table = table.select("tbody tr");

            // Iterar sobre as linhas da tabela
            for (Element linha : linhas_table) {
                // Obtenha a célula da linha
                Elements celula = linha.select("td");

                // Criar um mapa para representar a linha
                Map<String, String> rowMap = new LinkedHashMap<>();

                // Adicione os dados ao mapa
                rowMap.put("Papel", celula.get(0).text());
                rowMap.put("Cotação", celula.get(1).text());
                rowMap.put("P/L", celula.get(2).text());
                rowMap.put("PV/P", celula.get(3).text());
                rowMap.put("PSR", celula.get(4).text());
                rowMap.put("Div.Yield", celula.get(5).text());
                rowMap.put("P/Ativo", celula.get(6).text());
                rowMap.put("P/Cap.Giro", celula.get(7).text());
                rowMap.put("P/EBIT", celula.get(8).text());
                rowMap.put("P/Ativo Circ.Liq", celula.get(9).text());
                rowMap.put("EV/EBIT", celula.get(10).text());
                rowMap.put("EV/EBITDA", celula.get(11).text());
                rowMap.put("Mrg Ebit", celula.get(12).text());
                rowMap.put("Mrg. Líq.", celula.get(13).text());
                rowMap.put("Liq. Corr.", celula.get(14).text());
                rowMap.put("ROIC", celula.get(15).text());
                rowMap.put("ROE", celula.get(16).text());
                rowMap.put("Liq.2meses", celula.get(17).text());
                rowMap.put("Patrim. Líq", celula.get(18).text());
                rowMap.put("Dív.Brut/ Patrim.", celula.get(19).text());
                rowMap.put("Cresc. Rec.5a", celula.get(20).text());

                // Adicione o mapa à lista
                jsonData.add(rowMap);
            }
        }
        // Retornar lista em json
        return jsonData;
    }
    
}
