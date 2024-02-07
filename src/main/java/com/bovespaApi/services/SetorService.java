package com.bovespaApi.services;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SetorService {
    // SOLID - Single Responsibility Principle
    // Aqui realiza é setagem de linha(tr) da tabela no Json
    // A regra de negócio é separada do método principal
    private void setarDadoDaTabelaNoJson(Elements linha, List<Map<String, String>> data) {

        // Cria um mapa para representar a linha
        Map<String, String> rowMap = new LinkedHashMap<>();

        // Adiciona os dados ao mapa
        rowMap.put("Papel", linha.get(0).text());
        rowMap.put("Empresa", linha.get(0).select("span").attr("title"));
        rowMap.put("Cotação", linha.get(1).text());
        rowMap.put("P/L", linha.get(2).text());
        rowMap.put("PV/P", linha.get(3).text());
        rowMap.put("PSR", linha.get(4).text());
        rowMap.put("Div.Yield", linha.get(5).text());
        rowMap.put("P/Ativo", linha.get(6).text());
        rowMap.put("P/Cap.Giro", linha.get(7).text());
        rowMap.put("P/EBIT", linha.get(8).text());
        rowMap.put("P/Ativo Circ.Liq", linha.get(9).text());
        rowMap.put("EV/EBIT", linha.get(10).text());
        rowMap.put("EV/EBITDA", linha.get(11).text());
        rowMap.put("Mrg Ebit", linha.get(12).text());
        rowMap.put("Mrg. Líq.", linha.get(13).text());
        rowMap.put("Liq. Corr.", linha.get(14).text());
        rowMap.put("ROIC", linha.get(15).text());
        rowMap.put("ROE", linha.get(16).text());
        rowMap.put("Liq.2meses", linha.get(17).text());
        rowMap.put("Patrim. Líq", linha.get(18).text());
        rowMap.put("Dív.Brut/ Patrim.", linha.get(19).text());
        rowMap.put("Cresc. Rec.5a", linha.get(20).text());

        // Adiciona o mapa à lista
        data.add(rowMap);
    }

    // Retorna todos os papeis por setor
    public List<String> getPapeis(String setor) throws IOException {

        // Verifica se o setor é válido e gerar uma exceção
        if(Integer.parseInt(setor) > 43 || Integer.parseInt(setor) < 0){
            throw new IndexOutOfBoundsException("Parece que você digitou um setor que não existe, verifique a documentação para saber os setores disponíveis");
        }

        // URL do site da tabela
        String url = "https://www.fundamentus.com.br/resultado.php";

        // Criar uma conexão e passa parâmetros
        // Cada setor tem uma chave própria; Na documentação tem todos os setores por número
        Connection connection = Jsoup.connect(url).data("setor", setor).data("negociada", "ON");

        // Conecta ao site via POST e obtém o HTML
        Document document = connection.post();

        // Seleciona a tabela
        Element table = document.select("table").first();

        // Cria uma lista para armazenar os dados da tabela
        List<String> listData = new ArrayList<>();

        // Verifica se a tabela foi encontrada
        if (table != null) {
            // Obter todas as linhas da tabela
            Elements linhas_table = table.select("tbody tr");

            // Itera sobre as linhas da tabela
            for (Element linha : linhas_table) {
                // Obtém a célula da linha
                Elements celula = linha.select("td");

                // Retira o nome da EMPRESA do HTML e adiciona o papel à lista
                listData.add(celula.get(0).text());
            }
        }
        // Retornar lista
        return listData;
    }

    // Retorna todos os papeis junto com os indicadores
    public List<Map<String, String>> getIndicadores(String setor) throws IOException {

        // Verifica se o setor é válido e gerar uma exceção
        if(Integer.parseInt(setor) > 43 || Integer.parseInt(setor) < 0){
            throw new IndexOutOfBoundsException("Parece que você digitou um setor que não existe, verifique a documentação para saber os setores disponíveis");
        }

        // URL do site da tabela
        String url = "https://www.fundamentus.com.br/resultado.php";

        // Criar uma conexão e passa parâmetros
        // Cada setor tem uma chave própria; na documentação tem todos os setores por número
        // A chave 'negociada' significa que a ação foi negociada nos últimos 2 meses na bolsa
        Connection connection = Jsoup.connect(url).data("setor", setor).data("negociada", "ON");

        // Conecta ao site via POST e obtém o HTML
        Document document = connection.post();

        // Seleciona a tabela
        Element table = document.select("table").first();

        // Cria uma lista para armazenar os dados da tabela
        List<Map<String, String>> data = new ArrayList<>();

        // Verifica se a tabela foi encontrada
        if (table != null) {
            // Obtém todas as linhas da tabela
            Elements linhas_table = table.select("tbody tr");

            // Itera sobre as linhas da tabela
            // O HTML já retorna todos os dados do setor que foram passados, portanto, não é necessário adicionar uma condição
            for (Element linha : linhas_table) {
                // Obtenha a célula da linha(tr)
                Elements linha_tr = linha.select("td");

                // Chama o método setarDadoDaTabelaNoJson
                setarDadoDaTabelaNoJson(linha_tr, data);
            }
        }
        // Retornar lista em json
        return data;
    }

}
