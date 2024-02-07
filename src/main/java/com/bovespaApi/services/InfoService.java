package com.bovespaApi.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class InfoService {
    // Retorna todos os Indicadores da bolsa de forma estática
    public List<Map<String, String>> getIndicadores(){
        // Cria um mapa para representar os setores
        Map<String, String> indicadores_list = new LinkedHashMap<>();

        indicadores_list.put("P/L", "1");
        indicadores_list.put("P/VP", "2");
        indicadores_list.put("PSR", "3");
        indicadores_list.put("Dividend Yield", "4");
        indicadores_list.put("P/Ativos", "5");
        indicadores_list.put("P/Cap. Giro", "6");
        indicadores_list.put("P/Ativo Circ. Liq.", "7");
        indicadores_list.put("P/EBIT", "8");
        indicadores_list.put("EV/EBIT", "9");
        indicadores_list.put("EV/EBITDA", "10");
        indicadores_list.put("Margem EBIT", "11");
        indicadores_list.put("Margem Líquida", "12");
        indicadores_list.put("Liquidez Corrente", "13");
        indicadores_list.put("ROIC", "14");
        indicadores_list.put("ROE", "15");
        indicadores_list.put("Liquidez das Ações", "16");
        indicadores_list.put("Patrimônio Líquido", "17");
        indicadores_list.put("Dív. Bruta sobre o Patrimônio Líquido", "18");
        indicadores_list.put("Tx. de crescimento da Receita Líq", "19");

        // Cria uma lista para armazenar os setores
        List indicadores = new ArrayList<>();

        // Adiciona o mapa à lista
        indicadores.add(indicadores_list);

        // Retornar lista (JSON)
        return indicadores;
    }

    // Retorna todos os setores da bolsa de forma estática
    public List<Map<String, String>> getSetores(){
        // Cria um mapa para representar os setores
        Map<String, String> setores_list = new LinkedHashMap<>();

        setores_list.put("Agropecuária", "1");
        setores_list.put("Água e Saneamento", "2");
        setores_list.put("Alimentos Processados", "3");
        setores_list.put("Serv.Méd.Hospit. Análises e Diagnósticos", "4");
        setores_list.put("Automóveis e Motocicletas", "5");
        setores_list.put("Bebidas", "6");
        setores_list.put("Comércio", "7");
        setores_list.put("Comércio e Distribuição", "8");
        setores_list.put("Computadores e Equipamentos", "9");
        setores_list.put("Construção Civil", "10");
        setores_list.put("Construção e Engenharia", "11");
        setores_list.put("Diversos", "12");
        setores_list.put("Embalagens", "13");
        setores_list.put("Energia Elétrica", "14");
        setores_list.put("Equipamentos", "15");
        setores_list.put("Exploração de Imóveis", "16");
        setores_list.put("Gás", "17");
        setores_list.put("Holdings Diversificadas", "18");
        setores_list.put("Hoteis e Restaurantes", "19");
        setores_list.put("Intermediários Financeiros", "20");
        setores_list.put("Madeira e Papel", "21");
        setores_list.put("Máquinas e Equipamentos", "22");
        setores_list.put("Materiais Diversos", "23");
        setores_list.put("Material de Transporte", "24");
        setores_list.put("Medicamentos e Outros Produtos", "25");
        setores_list.put("Mídia", "26");
        setores_list.put("Mineração", "27");
        setores_list.put("Outros", "28");
        setores_list.put("Outros Títulos", "29");
        setores_list.put("Petróleo, Gás e Biocombustíveis", "30");
        setores_list.put("Previdência e Seguros", "31");
        setores_list.put("Produtos de Uso Pessoal e de Limpeza", "32");
        setores_list.put("Programas e Serviços", "33");
        setores_list.put("Químicos", "34");
        setores_list.put("Securitizadoras de Recebíveis", "35");
        setores_list.put("Serviços Diversos", "36");
        setores_list.put("Serviços Financeiros Diversos", "37");
        setores_list.put("Siderurgia e Metalurgia", "38");
        setores_list.put("Tecidos, Vestuário e Calçados", "39");
        setores_list.put("Telecomunicações", "40");
        setores_list.put("Transporte", "41");
        setores_list.put("Utilidades Domésticas", "42");
        setores_list.put("Viagens e Lazer", "43");

        // Cria uma lista para armazenar os setores
        List setores = new ArrayList<>();

        // Adiciona o mapa à lista
        setores.add(setores_list);

        // Retornar lista (JSON)
        return setores;
    }
}
