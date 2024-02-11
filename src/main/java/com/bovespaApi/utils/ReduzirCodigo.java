package com.bovespaApi.utils;

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
}
