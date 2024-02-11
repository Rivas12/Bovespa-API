package com.bovespaApi.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class ReduzirCodigo {

    public static void EncontrarTabelaEAdicionarALista(String url, String tipoRetorno, List<?> data, Map<String, String> dadosPost) throws IOException {
        Connection connection = Jsoup.connect(url).data("negociada", "ON");
        // Adicionar os pares de chave-valor da dataMap à conexão
        for (Map.Entry<String, String> entry : dadosPost.entrySet()) {
            connection.data(entry.getKey(), entry.getValue());
        }
        Document document = connection.post();
        Element table = document.select("table").first();

        if (table != null) {
            Elements linhasTable = table.select("tbody tr");

            if ("papeis".equals(tipoRetorno)) {
                for (Element linha : linhasTable) {
                    Elements celula = linha.select("td");
                    // Adiciona os dados na lista
                    ((List<String>) data).add(celula.get(0).text());
                }

            } else if ("empresas".equals(tipoRetorno)) {
                for (Element linha : linhasTable) {
                    Elements celula = linha.select("td");
                    // Adiciona os dados na lista
                    ((List<String>) data).add(celula.get(0).select("span").attr("title"));
                }

            } else if ("indicadores".equals(tipoRetorno)) {
                for (Element linha : linhasTable) {
                    Elements celula = linha.select("td");
                    Map<String, String> rowMap = new LinkedHashMap<>();

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

                    ((List<Map<String, String>>) data).add(rowMap);
                }
            }
        } else {
            throw new IOException("Tabela não encontrada na página.");
        }
    }

    public static void RemoverDuplicadas(List<String> data) {
        Set<String> set = new HashSet<>(data);
        data.clear();
        data.addAll(set);
    }

    // Verifica se o valor está dentro do range da lista
    public static void VerificaRangeDaLista(String nomeIndice, String valor) {
        if (nomeIndice.equals("setor")) {
            if (Integer.parseInt(valor) < 0 || Integer.parseInt(valor) > 43) {
                throw new IndexOutOfBoundsException("Parece que você digitou um setor que não existe, verifique a documentação ou use /setores para saber os setores disponíveis");
            }
        } else if (nomeIndice.equals("indicadores")) {
            if (Integer.parseInt(valor) < 0 || Integer.parseInt(valor) > 19) {
                throw new IndexOutOfBoundsException("Parece que você digitou um indicador que não existe, verifique a documentação ou use /setores para saber os setores disponíveis");
            }
        } else {
            throw new IllegalArgumentException("O nome do índice não é válido");
        }
    }

    // Pesquisa e adiciona a lista
    public static void PesquisaEAdicionarALista(String url, String termo, List<Map<String, String>> data) throws IOException {
        // Conexão inicial com o URL especificado
        Connection connection = Jsoup.connect(url).data("negociada", "ON");


        // Realiza a requisição POST e obtém o documento HTML
        Document document = connection.post();

        // Seleciona a primeira tabela no documento, se houver
        Element table = document.selectFirst("table");

        // Verifica se a tabela foi encontrada
        if (table != null) {
            // Obtém as linhas da tabela
            Elements linhasTable = table.select("tbody tr");

            double maiorSimilaridade = 0;

            // Itera sobre as linhas da tabela
            for (Element linha : linhasTable) {
                // Obtém o nome da empresa da linha e prepara para cálculo de similaridade
                String empresa = linha.selectFirst("td").selectFirst("span").attr("title")
                        .toLowerCase().replaceAll("\\s|banco|s\\.a\\.|sa|s/a|uni|-", "");

                // Calcula a similaridade entre o termo de pesquisa e o nome da empresa
                double similaridade = StringUtils.getJaroWinklerDistance(termo.toLowerCase(), empresa);

                // Verifica se a similaridade é alta o suficiente ou se o termo está contido na empresa
                if (similaridade >= 0.80 || empresa.contains(termo.toLowerCase())) {
                    // Cria um mapa para armazenar os dados da linha
                    Map<String, String> rowMap = new LinkedHashMap<>();

                    // Obtém e armazena os dados da linha no mapa
                    Elements colunas = linha.select("td");
                    rowMap.put("Papel", colunas.get(0).text());
                    rowMap.put("Empresa", colunas.get(0).selectFirst("span").attr("title"));
                    rowMap.put("Cotação", colunas.get(1).text());
                    rowMap.put("P/L", colunas.get(2).text());
                    rowMap.put("P/VP", colunas.get(3).text());
                    rowMap.put("PSR", colunas.get(4).text());
                    rowMap.put("Div.Yield", colunas.get(5).text());
                    rowMap.put("P/Ativo", colunas.get(6).text());
                    rowMap.put("P/Cap.Giro", colunas.get(7).text());
                    rowMap.put("P/EBIT", colunas.get(8).text());
                    rowMap.put("P/Ativo Circ.Liq", colunas.get(9).text());
                    rowMap.put("EV/EBIT", colunas.get(10).text());
                    rowMap.put("EV/EBITDA", colunas.get(11).text());
                    rowMap.put("Mrg Ebit", colunas.get(12).text());
                    rowMap.put("Mrg. Líq.", colunas.get(13).text());
                    rowMap.put("Liq. Corr.", colunas.get(14).text());
                    rowMap.put("ROIC", colunas.get(15).text());
                    rowMap.put("ROE", colunas.get(16).text());
                    rowMap.put("Liq.2meses", colunas.get(17).text());
                    rowMap.put("Patrim. Líq", colunas.get(18).text());
                    rowMap.put("Dív.Brut/ Patrim.", colunas.get(19).text());
                    rowMap.put("Cresc. Rec.5a", colunas.get(20).text());


                    // Adiciona o mapa à lista de dados
                    data.add(rowMap);

                    // Atualiza a maior similaridade, se necessário
                    if (maiorSimilaridade <= similaridade) {
                        maiorSimilaridade = similaridade;

                        // Move o último elemento para o início da lista
                        data.add(0, data.remove(data.size() - 1));
                    }
                }
            }
        } else {
            throw new IOException("Tabela não encontrada na página.");
        }
    }

    public static void RetornoUnico(String url, String papel, String tipoRetorno, List<Map<String, String>> data) throws IOException {
        if(tipoRetorno.equals("indicadores")){

            Connection connection = Jsoup.connect(url).data("negociada", "ON");

            Document document = connection.post();
            Element table = document.select("table").first();

            if (table != null) {
                Elements linhasTable = table.select("tbody tr");

                for (Element linha : linhasTable) {

                    Elements celula = linha.select("td");
                    // Aqui é a condição para verificar se o papel descrito na URL é igual ao da linha da TABLE
                    if (celula.get(0).text().equals(papel.toUpperCase())) {

                        Map<String, String> rowMap = new LinkedHashMap<>();

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

                        data.add(rowMap);
                    }

                }
            }
        } else if (tipoRetorno.equals("proventos")) {

            // Criar uma conexão e passa parâmetros
            Connection connection = Jsoup.connect(url).data("papel", papel.toUpperCase()).data("negociada", "ON");

            // Conecta ao site via GET e obtém o HTML
            Document document = connection.get();

            // Selecionar a tabela
            Element table = document.select("#resultado").first();

            // Verifica se a tabela foi encontrada
            if (table != null) {

                // Obtém todas as linhas da tabela
                Elements linhas_table = table.select("tbody tr");

                // Iterar sobre as linhas da tabela
                for (Element linha : linhas_table) {

                    // Obtém a célula da linha
                    Elements linha_tr = linha.select("td");
                    // Cria um mapa para representar a linha
                    Map<String, String> rowMap = new LinkedHashMap<>();

                    // Adiciona os dados ao mapa
                    rowMap.put("Valor", linha_tr.get(1).text());
                    rowMap.put("Tipo", linha_tr.get(2).text());
                    rowMap.put("Data do anuncio", linha_tr.get(0).text());
                    rowMap.put("Data de pagamento", linha_tr.get(3).text());
                    rowMap.put("Por quantas ações", linha_tr.get(4).text());

                    data.add(rowMap);
                }

            }
        }
        // Verifica se a lista está vazia e gera uma exceção
        if(data.isEmpty()){
            throw new NoSuchElementException("Parece que você digitou um papel que não existe, verifique se o papel existe e se está escrito corretamente");
        }
    }

}


