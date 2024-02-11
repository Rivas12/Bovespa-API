package com.bovespaApi.services;

import com.bovespaApi.utils.ReduzirCodigo;

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
public class FiltroService {

    public List<Map<String, String>> getFiltro(String plMin, String plMax, String pvpMin, String pvpMax, String psrMin, String psrMax, String divyMin, String divyMax, String pativosMin, String pativosMax, String pcapgiroMin, String pcapgiroMax, String pebitMin, String pebitMax, String fgrahMin, String fgrahMax, String firmaEbitMin, String firmaEbitMax, String firmaEbitdaMin, String firmaEbitdaMax, String margemEbitMin, String margemEbitMax, String margemLiqMin, String margemLiqMax, String liqcorrMin, String liqcorrMax, String roicMin, String roicMax, String roeMin, String roeMax, String liqMin, String liqMax, String patrimMin, String patrimMax, String divbrutaMin, String divbrutaMax, String txCrescRecMin, String txCrescRecMax, String setor, String negociada, String ordem) throws IOException {

        // Cria um mapa para armazenar os dados do POST
        Map<String, String> dadosPost = new HashMap<>();
        String[] chaves = {"pl_min", "pl_max", "pvp_min", "pvp_max", "psr_min", "psr_max", "divy_min", "divy_max", "pativos_min", "pativos_max", "pcapgiro_min", "pcapgiro_max", "pebit_min", "pebit_max", "fgrah_min", "fgrah_max", "firma_ebit_min", "firma_ebit_max", "firma_ebitda_min", "firma_ebitda_max", "margemebit_min", "margemebit_max", "margemliq_min", "margemliq_max", "liqcorr_min", "liqcorr_max", "roic_min", "roic_max", "roe_min", "roe_max", "liq_min", "liq_max", "patrim_min", "patrim_max", "divbruta_min", "divbruta_max", "tx_cresc_rec_min", "tx_cresc_rec_max", "setor", "negociada", "ordem"};
        String[] valores = {plMin, plMax, pvpMin, pvpMax, psrMin, psrMax, divyMin, divyMax, pativosMin, pativosMax, pcapgiroMin, pcapgiroMax, pebitMin, pebitMax, fgrahMin, fgrahMax, firmaEbitMin, firmaEbitMax, firmaEbitdaMin, firmaEbitdaMax, margemEbitMin, margemEbitMax, margemLiqMin, margemLiqMax, liqcorrMin, liqcorrMax, roicMin, roicMax, roeMin, roeMax, liqMin, liqMax, patrimMin, patrimMax, divbrutaMin, divbrutaMax, txCrescRecMin, txCrescRecMax, setor, negociada, ordem};

        // Adiciona os pares de chave-valor ao mapa
        for (int i = 0; i < chaves.length; i++) {
            dadosPost.put(chaves[i], valores[i]);
        }

        List<Map<String, String>> data = new ArrayList<>();
        ReduzirCodigo.EncontrarTabelaEAdicionarALista("https://www.fundamentus.com.br/resultado.php", "indicadores", data, dadosPost);
        return data;
    }

    // Retorna todos os papéis pela ordem de maior similaridade que foi passado
    public  List<Map<String, String>> getPapeisPesquisa(String termo) throws IOException {

        List<Map<String, String>> data = new ArrayList<>();
        Map<String, String> dadosPost = new HashMap<>();
        ReduzirCodigo.PesquisaEAdicionarALista("https://www.fundamentus.com.br/resultado.php", termo, data, dadosPost);
        // Retornar lista em json
        return data;
        
    }

}
