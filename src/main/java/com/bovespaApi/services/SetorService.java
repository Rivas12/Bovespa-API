package com.bovespaApi.services;

import com.bovespaApi.utils.ReduzirCodigo;

import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class SetorService {
    // SOLID - Single Responsibility Principle
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
