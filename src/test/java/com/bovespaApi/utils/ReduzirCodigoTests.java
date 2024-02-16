package com.bovespaApi.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
