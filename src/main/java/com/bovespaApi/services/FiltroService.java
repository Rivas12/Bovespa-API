package com.bovespaApi.services;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.net.SocketOption;
import java.util.*;

@Service
public class FiltroService {

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
                Elements linhas_tabela = table.select("tbody tr");

                // Seta os dados da tabela no Json
                for (Element linha : linhas_tabela) {
                    // Obtém a célula da linha
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

                    // Adiciona o mapa à lista
                    data.add(rowMap);
                }

        } else {
            // Se a tabela não foi encontrada, lança uma exceção
            throw new NoSuchElementException("Não foi possível encontrar a tabela, verifique se o site está funcionando corretamente");
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

                    // Adiciona o mapa à lista
                    data.add(rowMap);

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
