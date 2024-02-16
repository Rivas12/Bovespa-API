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

}
