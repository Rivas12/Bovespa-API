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

// Para evitar retornos de dados irrelevantes, as empresas que não foram negociadas nos últimos 2 meses na bolsa foram excluídas desta API
// Todas as requisições feitas são enviadas com um parâmetro 'negociada' (ON) para filtrar essas empresas irrelevantes
@Service
public class AcoesService {

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
    public List<Map<String, String>> getPapeisComIndicadores() throws IOException{

        List<Map<String, String>> data = new ArrayList<>();

        // URL do site da tabela
        String url = "https://www.fundamentus.com.br/resultado.php";

        // Criar uma conexão e passa parâmetros
        Connection connection = Jsoup.connect(url).data("negociada", "ON");

        // Conecta ao site via POST e obtém o HTML
        Document document = connection.post();

        // Seleciona a tabela
        Element table = document.select("table").first();
        if (table != null) {
            // Obtém todas as linhas da tabela
            Elements linhas_tabela = table.select("tbody tr");

            // Seta os dados da tabela no Json
            for (Element linha : linhas_tabela) {
                // Obtém a célula da linha
                Elements linha_tr = linha.select("td");

                // Chama o método setarDadoDaTabelaNoJson
                setarDadoDaTabelaNoJson(linha_tr, data);
            }
        }

        // Retornar lista em json
        return data;
    }


    // Retorna um papel que foi passado por parâmetro, juntamente com os indicadores
    public List<Map<String, String>> getPapelComIndicadores(String papel) throws IOException {

        // URL do site da tabela
        String url = "https://www.fundamentus.com.br/resultado.php";

        // Criar uma conexão e passa parâmetros
        Connection connection = Jsoup.connect(url).data("negociada", "ON");

        // Conecta ao site via POST e obtém o HTML
        Document document = connection.post();

        // Selecionar a tabela
        Element table = document.select("table").first();

        // Cria uma lista para armazenar os dados da tabela
        List<Map<String, String>> data = new ArrayList<>();

        // Verifica se a tabela foi encontrada
        if (table != null) {

            // Obtém todas as linhas da tabela
            Elements linhas_table = table.select("tbody tr");

            // Iterar sobre as linhas da tabela
            for (Element linha : linhas_table) {
                // Obtém a célula da linha
                Elements linha_tr = linha.select("td");

                // Aqui é a condição para verificar se o papel descrito na URL é igual ao da linha da TABLE
                if (linha_tr.get(0).text().equals(papel)) {

                    // Chama o método setarDadoDaTabelaNoJson
                    setarDadoDaTabelaNoJson(linha_tr, data);

                }
            }
        }
        // Verifica se a lista está vazia e gera uma exceção
        if(data.isEmpty()){
            throw new NoSuchElementException("Parece que você digitou um papel que não existe, verifique se o papel existe e se está escrito corretamente");
        }
        // Retornar lista em json
        return data;
    }

    // Retorna todos os papéis pela ordem do indicador que foi passado
    public List<Map<String, String>> getPapelPorOrdem(String chave, String cresc_desc) throws IOException {

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
        List<Map<String, String>> data = new ArrayList<>();

        // Verifica se a tabela foi encontrada
        if (table != null) {
            // Obtém todas as linhas(tr) da tabela
            Elements linhas_table = table.select("tbody tr");

            // Iterar sobre as linhas da tabela
            for (Element linha : linhas_table) {
                // Célula da linha(tr) do HTML
                Elements linha_tr = linha.select("td");

                // Cria um mapa para representar a linha
                Map<String, String> rowMap = new LinkedHashMap<>();

                // Adiciona os dados ao mapa
                rowMap.put("Papel", linha_tr.get(0).text());
                rowMap.put("Empresa", linha_tr.get(0).select("span").attr("title"));
                rowMap.put("Cotação", linha_tr.get(1).text());
                rowMap.put("P/L", linha_tr.get(2).text());
                rowMap.put("PV/P", linha_tr.get(3).text());
                rowMap.put("PSR", linha_tr.get(4).text());
                rowMap.put("Div.Yield", linha_tr.get(5).text());
                rowMap.put("P/Ativo", linha_tr.get(6).text());
                rowMap.put("P/Cap.Giro", linha_tr.get(7).text());
                rowMap.put("P/EBIT", linha_tr.get(8).text());
                rowMap.put("P/Ativo Circ.Liq", linha_tr.get(9).text());
                rowMap.put("EV/EBIT", linha_tr.get(10).text());
                rowMap.put("EV/EBITDA", linha_tr.get(11).text());
                rowMap.put("Mrg Ebit", linha_tr.get(12).text());
                rowMap.put("Mrg. Líq.", linha_tr.get(13).text());
                rowMap.put("Liq. Corr.", linha_tr.get(14).text());
                rowMap.put("ROIC", linha_tr.get(15).text());
                rowMap.put("ROE", linha_tr.get(16).text());
                rowMap.put("Liq.2meses", linha_tr.get(17).text());
                rowMap.put("Patrim. Líq", linha_tr.get(18).text());
                rowMap.put("Dív.Brut/ Patrim.", linha_tr.get(19).text());
                rowMap.put("Cresc. Rec.5a", linha_tr.get(20).text());

                // Verifica se o parâmetro 'cresc_desc' é válido e gera uma exceção
                if ("cresc".equals(cresc_desc)) {
                    // Adiciona o map ao início da lista para ordenar de forma crescente
                    data.add(0, rowMap);
                } else if ("desc".equals(cresc_desc)) {
                    // Adiciona o map ao final da lista para ordenar de forma decrescente
                    data.add(rowMap);
                } else {
                    // Gera uma exceção
                    throw new IllegalArgumentException("Parece que você digitou um parâmetro que não existe, somente 'cresc' ou 'desc' são aceitos");
                }

            }
        }
        // Retornar lista em json
        return data;
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
        List<Map<String, String>> data = new ArrayList<>();

        // Verifica se a tabela foi encontrada
        if (table != null) {

            // Obtém todas as linhas da tabela
            Elements linhas_table = table.select("tbody tr");

            // Esse Double é utilizado para calcular a maior similaridade com a célula que está sendo verificada
            double maior_similaridade = 0;

            // Para cada 'loop', a similaridade do termo e da empresa é calculada; quanto maior a similaridade, maior será sua hierarquia na lista
            for (Element linha : linhas_table) {

                // Obtém a célula da linha
                Elements linha_tr = linha.select("td");

                // Recupera o nome da empresa, retira as siglas e muda a String para LowCase para melhorar o calcúlo da similaridade
                String empresa = linha_tr.get(0).select("span").attr("title").toLowerCase().replaceAll("\\s", "").replaceAll("\\s|banco|s\\.a\\.|sa|s/a|uni|-", "");

                // Aqui é calculado o quanto o Termo é Parecido com a Empresa da célula. Sendo que 1.0 é idêntico
                double similaridade = StringUtils.getJaroWinklerDistance(termo.toLowerCase(), empresa);

                // Aqui, empresa.contains() verifica se o termo está dentro da String da empresa
                if (similaridade >= 0.80 || empresa.contains(termo.toLowerCase())) {

                    // Chama o método setarDadoDaTabelaNoJson
                    setarDadoDaTabelaNoJson(linha_tr, data);

                    // Aqui é feita a ordenação; quanto maior a similaridade, maior será a hierarquia na lista
                    if (maior_similaridade <= similaridade) {

                        maior_similaridade = similaridade;

                        // Remove o último elemento e armazena em uma variável
                        Map<String, String> ultimoElemento = data.remove(data.size() - 1);

                        // Adiciona essa variável no início da lista
                        data.add(0, ultimoElemento);
                    }
                }

            }
        }
        // Retornar lista em json
        return data;
    }


}
