package com.bovespaApi.services;

import com.bovespaApi.utils.ReduzirCodigo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class AcaoService {


    // Retorna um papel que foi passado por parâmetro, juntamente com os indicadores
    public List<Map<String, String>> getPapel(String papel) throws IOException{

        List<Map<String, String>> data = new ArrayList<>();
        ReduzirCodigo.RetornoUnico("https://www.fundamentus.com.br/resultado.php", papel, "indicadores", data);
        return data;

    }

    // Retorna os proventos de um papel que foi passado por parâmetro
    public List<Map<String, String>> getProventos(String papel) throws IOException{

        List<Map<String, String>> data = new ArrayList<>();
        ReduzirCodigo.RetornoUnico("https://www.fundamentus.com.br/proventos.php", papel, "proventos", data);
        return data;

    }
}
