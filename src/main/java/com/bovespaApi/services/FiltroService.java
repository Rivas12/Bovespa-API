package com.bovespaApi.services;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.*;

@Service
public class FiltroService {

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

    public List<Map<String, String>> getFiltro(
            String plMin,
            String plMax,
            String pvpMin,
            String pvpMax,
            String psrMin,
            String psrMax,
            String divyMin,
            String divyMax,
            String pativosMin,
            String pativosMax,
            String pcapgiroMin,
            String pcapgiroMax,
            String pebitMin,
            String pebitMax,
            String fgrahMin,
            String fgrahMax,
            String firmaEbitMin,
            String firmaEbitMax,
            String firmaEbitdaMin,
            String firmaEbitdaMax,
            String margemEbitMin,
            String margemEbitMax,
            String margemLiqMin,
            String margemLiqMax,
            String liqcorrMin,
            String liqcorrMax,
            String roicMin,
            String roicMax,
            String roeMin,
            String roeMax,
            String liqMin,
            String liqMax,
            String patrimMin,
            String patrimMax,
            String divbrutaMin,
            String divbrutaMax,
            String txCrescRecMin,
            String txCrescRecMax,
            String setor,
            String negociada,
            String ordem
    ) throws IOException{

        // URL do site da tabela
        String url = "https://www.fundamentus.com.br/resultado.php";

        // Lista de variáveis
        String[] variaveis = {divyMin, divyMax, margemLiqMin, margemLiqMax, firmaEbitdaMin, firmaEbitdaMax, roeMin, roeMax, txCrescRecMin, txCrescRecMax};

        // Tratamento das variáveis
        for (int i = 0; i < variaveis.length; i++) {
            if (variaveis[i].isEmpty()) {
                variaveis[i] = "0.";
            } else {
                variaveis[i] = "0." + variaveis[i];
            }
        }

        // Criar uma conexão e passa parâmetros
        Connection connection = Jsoup.connect(url)
                .data("pl_min", plMin)
                .data("pl_max", plMax)
                .data("pvp_min", pvpMin)
                .data("pvp_max", pvpMax)
                .data("psr_min", psrMin)
                .data("psr_max", psrMax)
                .data("divy_min", divyMin)
                .data("divy_max", divyMax)
                .data("pativos_min", pativosMin)
                .data("pativos_max", pativosMax)
                .data("pcapgiro_min", pcapgiroMin)
                .data("pcapgiro_max", pcapgiroMax)
                .data("pebit_min", pebitMin)
                .data("pebit_max", pebitMax)
                .data("fgrah_min", fgrahMin)
                .data("fgrah_max", fgrahMax)
                .data("firma_ebit_min", firmaEbitMin)
                .data("firma_ebit_max", firmaEbitMax)
                .data("firma_ebitda_min", firmaEbitdaMin)
                .data("firma_ebitda_max", firmaEbitdaMax)
                .data("margemebit_min", margemEbitMin)
                .data("margemebit_max", margemEbitMax)
                .data("margemliq_min", margemLiqMin)
                .data("margemliq_max", margemLiqMax)
                .data("liqcorr_min", liqcorrMin)
                .data("liqcorr_max", liqcorrMax)
                .data("roic_min", roicMin)
                .data("roic_max", roicMax)
                .data("roe_min", roeMin)
                .data("roe_max", roeMax)
                .data("liq_min", liqMin)
                .data("liq_max", liqMax)
                .data("patrim_min", patrimMin)
                .data("patrim_max", patrimMax)
                .data("divbruta_min", divbrutaMin)
                .data("divbruta_max", divbrutaMax)
                .data("tx_cresc_rec_min", txCrescRecMin)
                .data("tx_cresc_rec_max", txCrescRecMax)
                .data("setor", setor)
                .data("negociada", negociada)
                .data("ordem", ordem);

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
        // Verifica se a lista está vazia e gera uma exceção
        if(data.isEmpty()){
            throw new NoSuchElementException("Parece que você digitou um papel que não existe, verifique se o papel existe e se está escrito corretamente");
        }
        // Retornar lista em json
        return data;
    }
}
