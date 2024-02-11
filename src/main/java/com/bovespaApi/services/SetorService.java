package com.bovespaApi.services;

import com.bovespaApi.utils.ReduzirCodigo;

import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class SetorService {

    // Retorna todos os papeis por setor
    public List<String> getPapeis(String setor) throws IOException {

        ReduzirCodigo.VerificaRangeDaLista("setor", setor);

        Map<String, String> dadosPost = new HashMap<>();
        dadosPost.put("setor", setor);
        List<String> data = new ArrayList<>();
        ReduzirCodigo.EncontrarTabelaEAdicionarALista("https://www.fundamentus.com.br/resultado.php", "papeis", data, dadosPost);
        return data;

    }

    // Retorna todos os papeis junto com os indicadores
    public List<Map<String, String>> getIndicadores(String setor) throws IOException {

        ReduzirCodigo.VerificaRangeDaLista("setor", setor);

        Map<String, String> dadosPost = new HashMap<>();
        dadosPost.put("setor", setor);
        List<Map<String, String>> data = new ArrayList<>();
        ReduzirCodigo.EncontrarTabelaEAdicionarALista("https://www.fundamentus.com.br/resultado.php", "indicadores", data, dadosPost);
        return data;
    }

    // Retorna todos os nomes das empresas por setor
    public List<String> getEmpresas(String setor) throws IOException {

        ReduzirCodigo.VerificaRangeDaLista("setor", setor);

        Map<String, String> dadosPost = new HashMap<>();
        dadosPost.put("setor", setor);
        List<String> data = new ArrayList<>();
        ReduzirCodigo.EncontrarTabelaEAdicionarALista("https://www.fundamentus.com.br/resultado.php", "empresas", data, dadosPost);
        ReduzirCodigo.RemoverDuplicadas(data);
        return data;
    }
}
