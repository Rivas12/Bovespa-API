package com.bovespaApi.services;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
// Para evitar retornos de dados irrelevantes, as empresas que não foram negociadas nos últimos 2 meses na bolsa foram excluídas desta API
// Todas as requisições feitas são enviadas com um parâmetro 'negociada' (ON) para filtrar essas empresas irrelevantes
public class AcoesService {

    // Retorna todos os papeis da bolsa
    public List<String> getPapeis() throws IOException {

        // URL do site da tabela
        String url = "https://www.fundamentus.com.br/resultado.php";

        // Criar uma conexão e passa parâmetros
        Connection connection = Jsoup.connect(url).data("negociada", "ON");

        // Conecta ao site via POST e obtém o HTML
        Document document = connection.post();

        // Seleciona a tabela
        Element table = document.select("table").first();

        // Cria uma lista para armazenar os dados da tabela
        List<String> listData = new ArrayList<>();

        // Verificar se a tabela foi encontrada
        if (table != null) {
            // Obter todas as linhas da tabela
            Elements linhas_table = table.select("tbody tr");

            // Itera sobre as linhas da tabela
            for (Element linha : linhas_table) {
                // Obtém a célula da linha
                Elements celula = linha.select("td");

                // Retira o papel do HTML e adiciona à lista
                listData.add(celula.get(0).text());
            }
        }
        // Retornar lista
        return listData;
    }

    // Retorna todos os papéis junto com os indicadores
    public  List<Map<String, String>> getPapeisEstatisticas() throws IOException {

        // URL do site da tabela
        String url = "https://www.fundamentus.com.br/resultado.php";

        // Criar uma conexão e passa parâmetros
        Connection connection = Jsoup.connect(url).data("negociada", "ON");

        // Conecta ao site via POST e obtém o HTML
        Document document = connection.post();

        // Seleciona a tabela
        Element table = document.select("table").first();

        // Cria uma lista para armazenar os dados da tabela
        List<Map<String, String>> jsonData = new ArrayList<>();

        // Verifica se a tabela foi encontrada
        if (table != null) {
            // Obtém todas as linhas da tabela
            Elements linhas_table = table.select("tbody tr");

            // Itera sobre as linhas da tabela
            for (Element linha : linhas_table) {
                // Obtém a célula da linha
                Elements celula = linha.select("td");

                // Cria um mapa para representar a linha
                Map<String, String> rowMap = new LinkedHashMap<>();

                // Adiciona os dados ao mapa
                rowMap.put("Papel", celula.get(0).text());
                rowMap.put("Empresa", celula.get(0).select("span").attr("title"));
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

                // Adiciona o mapa à lista
                jsonData.add(rowMap);
            }
        }
        // Retornar lista em json
        return jsonData;
    }

    // Retorna todos os papeis por setor
    public List<String> getPapeisPorSetor(String setor) throws IOException {

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
    public List<Map<String, String>> getPapeisPorSetorEstatisticas(String setor) throws IOException {

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
        List<Map<String, String>> jsonData = new ArrayList<>();

        // Verifica se a tabela foi encontrada
        if (table != null) {
            // Obtém todas as linhas da tabela
            Elements linhas_table = table.select("tbody tr");

            // Itera sobre as linhas da tabela
            // O HTML já retorna todos os dados do setor que foram passados, portanto, não é necessário adicionar uma condição
            for (Element linha : linhas_table) {
                // Obtenha a célula da linha(tr)
                Elements celula = linha.select("td");

                // Criar um mapa para representar a linha
                Map<String, String> rowMap = new LinkedHashMap<>();

                // Adicione os dados ao mapa
                rowMap.put("Papel", celula.get(0).text());
                rowMap.put("Empresa", celula.get(0).select("span").attr("title"));
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

    // Retorna um papel que foi passado por parâmetro, juntamente com os indicadores
    public List<Map<String, String>> getPapelEstatisticas(String papel) throws IOException {

        // URL do site da tabela
        String url = "https://www.fundamentus.com.br/resultado.php";

        // Criar uma conexão e passa parâmetros
        Connection connection = Jsoup.connect(url).data("negociada", "ON");

        // Conecta ao site via POST e obtém o HTML
        Document document = connection.post();

        // Selecionar a tabela
        Element table = document.select("table").first();

        // Cria uma lista para armazenar os dados da tabela
        List<Map<String, String>> jsonData = new ArrayList<>();

        // Verifica se a tabela foi encontrada
        if (table != null) {

            // Obtém todas as linhas da tabela
            Elements linhas_table = table.select("tbody tr");

            // Iterar sobre as linhas da tabela
            for (Element linha : linhas_table) {
                // Obtém a célula da linha
                Elements celula = linha.select("td");

                // Aqui é a condição para verificar se o papel descrito na URL é igual ao da linha da TABLE
                if (celula.get(0).text().equals(papel)) {

                    // Cria um mapa para representar a linha(tr)
                    Map<String, String> rowMap = new LinkedHashMap<>();

                    // Adicione os dados ao mapa
                    rowMap.put("Papel", celula.get(0).text());
                    rowMap.put("Empresa", celula.get(0).select("span").attr("title"));
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

                    // Adiciona o mapa à lista
                    jsonData.add(rowMap);

                    // Finaliza o For para retornar mais rapidamente
                    break;
                }
            }
        }
        // Retornar lista em json
        return jsonData;
    }

    // Retorna todos os papéis pela ordem do indicador que foi passado
    public List<Map<String, String>> getPapelPorOrdem(String chave) throws IOException {

        // URL do site da tabela
        String url = "https://www.fundamentus.com.br/resultado.php";

        // Criar uma conexão e passa parâmetros
        // Cada ordem tem uma chave própria, documentação tem todas as ordens por número
        Connection connection = Jsoup.connect(url).data("ordem", chave).data("negociada", "ON");

        // Conecta ao site via POST e obtém o HTML
        Document document = connection.post();

        // Selecionar a tabela
        Element table = document.select("table").first();

        // Criar uma lista para armazenar os dados da tabela
        List<Map<String, String>> jsonData = new ArrayList<>();

        // Verifica se a tabela foi encontrada
        if (table != null) {
            // Obtém todas as linhas(tr) da tabela
            Elements linhas_table = table.select("tbody tr");

            // Iterar sobre as linhas da tabela
            for (Element linha : linhas_table) {
                // Célula da linha(tr) do HTML
                Elements celula = linha.select("td");

                // Mapa para representar a linha
                Map<String, String> rowMap = new LinkedHashMap<>();

                // Adiciona os dados ao Map,
                rowMap.put("Papel", celula.get(0).text());
                rowMap.put("Empresa", celula.get(0).select("span").attr("title"));
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

    // Retorna todos os papéis pela ordem de maior similaridade que foi passado
    public  List<Map<String, String>> getPapeisPesquisa(String termo) throws IOException {

        // URL do site da tabela
        String url = "https://www.fundamentus.com.br/resultado.php";

        // Criar uma conexão e passa parâmetros
        Connection connection = Jsoup.connect(url).data("negociada", "ON");

        // Conecta ao site via POST e obtém o HTML
        Document document = connection.post();

        // Seleciona a tabela
        Element table = document.select("table").first();

        // Cria uma lista para armazenar os dados da tabela
        List<Map<String, String>> jsonData = new ArrayList<>();

        // Verifica se a tabela foi encontrada
        if (table != null) {

            // Obtém todas as linhas da tabela
            Elements linhas_table = table.select("tbody tr");

            // Esse Double é utilizado para calcular a maior similaridade com a célula que está sendo verificada
            double maior_similaridade = 0;

            // Para cada 'loop', a similaridade do termo e da empresa é calculada; quanto maior a similaridade, maior será sua hierarquia na lista
            for (Element linha : linhas_table) {

                // Obtém a célula da linha
                Elements celula = linha.select("td");

                // Recupera o nome da empresa, retira as siglas e muda a String para LowCase para melhorar o calcúlo da similaridade
                String empresa = celula.get(0).select("span").attr("title").toLowerCase().replaceAll("\\s", "").replaceAll("\\s|banco|s\\.a\\.|sa|s/a|uni|-", "");

                // Aqui é calculado o quanto o Termo é Parecido com a Empresa da célula. Sendo que 1.0 é idêntico
                double similaridade = StringUtils.getJaroWinklerDistance(termo.toLowerCase(), empresa);

                // Aqui o empresa.contains() vê se o termo está dentro da String da empresa
                if(similaridade >= 0.85 || empresa.contains(termo.toLowerCase())){

                    // Criar um mapa para representar a linha
                    Map<String, String> rowMap = new LinkedHashMap<>();

                    // Adicione os dados ao mapa
                    rowMap.put("Papel", celula.get(0).text());
                    rowMap.put("Empresa", celula.get(0).select("span").attr("title"));
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

                    // Aqui é feito o ordenação; quanto maior a similaridade maior será a hierarquia na lista
                    if(maior_similaridade <= similaridade){
                        maior_similaridade = similaridade;
                        // Adiciona o mapa à lista
                        jsonData.add(0,rowMap);
                    }else{
                        // Adicione o mapa à lista
                        jsonData.add(rowMap);
                    }

                }
            }
        }
        // Retornar lista em json
        return jsonData;
    } //

}
