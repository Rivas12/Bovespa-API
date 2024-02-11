package com.bovespaApi.services;

import com.bovespaApi.utils.ReduzirCodigo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;


// Para evitar retornos de dados irrelevantes, as empresas que não foram negociadas nos últimos 2 meses na bolsa foram excluídas desta API
// Todas as requisições feitas são enviadas com um parâmetro 'negociada' (ON) para filtrar essas empresas irrelevantes
@Service
public class BovespaService {

    // Retorna todos os papeis da bolsa
    public List<String> getPapeis() throws IOException {
        Map<String, String> dadosPost = new HashMap<>();
        List<String> data = new ArrayList<>();
        ReduzirCodigo.EncontrarTabelaEAdicionarALista("https://www.fundamentus.com.br/resultado.php", "papeis", data, dadosPost);
        return data;
    }

    // Retorna todos os papéis junto com os indicadores
    public List<Map<String, String>> getPapeisComIndicadores() throws IOException{
        Map<String, String> dadosPost = new HashMap<>();
        List<Map<String, String>> data = new ArrayList<>();
        ReduzirCodigo.EncontrarTabelaEAdicionarALista("https://www.fundamentus.com.br/resultado.php", "indicadores", data, dadosPost);
        return data;
    }

    // Retorna todos os nomes das empresas
    public List<String> getEmpresas() throws IOException {
        Map<String, String> dadosPost = new HashMap<>();
        List<String> data = new ArrayList<>();
        ReduzirCodigo.EncontrarTabelaEAdicionarALista("https://www.fundamentus.com.br/resultado.php", "empresas", data, dadosPost);
        ReduzirCodigo.RemoverDuplicadas(data);
        return data;
    }

}
