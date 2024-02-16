package com.bovespaApi.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.coyote.http11.Constants.a;
import static org.junit.jupiter.api.Assertions.*;

class ReduzirCodigoTests{

    @Test
    void testEncontrarTabelaEAdicionarALista() throws IOException  {
        Map<String, String> dadosPost = new HashMap<>();
        List<String> data = new ArrayList<>();
        ReduzirCodigo.EncontrarTabelaEAdicionarALista("https://www.fundamentus.com.br/resultado.php", "papeis", data, dadosPost);
        assertFalse(data.isEmpty());
    }

    @Test
    void testUrlInvalida() throws IOException  {
        Map<String, String> dadosPost = new HashMap<>();
        List<String> data = new ArrayList<>();
        assertThrows(IOException.class, () -> ReduzirCodigo.EncontrarTabelaEAdicionarALista("https://www.urlerrad4.com.br/resultado", "papeis", data, dadosPost));
    }

    @Test
    void testVerificaRangeDaLista() {
        assertDoesNotThrow(() -> ReduzirCodigo.VerificaRangeDaLista("setor", "10"));
        assertThrows(IndexOutOfBoundsException.class, () -> ReduzirCodigo.VerificaRangeDaLista("setor", "1000"));
    }

    @Test
    void testRemoverDuplicadas() {
        List<String> data = new ArrayList<>();
        data.add("teste");
        data.add("teste");
        data.add("teste");

        ReduzirCodigo.RemoverDuplicadas(data);
        assertEquals(1, data.size());
    }

    @Test
    void testPesquisaEAdicionarALista() throws IOException {
        List<Map<String, String>> data = new ArrayList<>();
        ReduzirCodigo.PesquisaEAdicionarALista("https://www.fundamentus.com.br/resultado.php", "petrobras", data);
        assertTrue(data.size() > 0);
        data.clear();
        // Pesquisa inválida pois o termo pesquisado não existe
        ReduzirCodigo.PesquisaEAdicionarALista("https://www.fundamentus.com.br/resultado.php", "hsdahdvbhajdwvhdvahjdwjhaDJHSBVAGHJSda", data);
        assertEquals(0, data.size());
    }

}
