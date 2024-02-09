package com.bovespaApi.controllers;


import com.bovespaApi.services.FiltroService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@Tag(name = "Filtros & Pesquisa", description = "Retorna informações com todos os filtros disponíveis.")
public class FiltroController {

    // Injeção de dependência do serviço de ações
    private final FiltroService filtroService;

    @Autowired
    // Construtor com a injeção de dependência do serviço de ações
    public FiltroController(FiltroService filtroService) {
        this.filtroService = filtroService;
    }

    // Método que retorna as ações com todos os filtros disponíveis
    @GetMapping("/filtro")
    public List<Map<String, String>> getFiltro(
            @Parameter(description = "P/L (Preço/Lucro) mínimo")
            @RequestParam(required = false, defaultValue = "") String pl_min,

            @Parameter(description = "P/L (Preço/Lucro) máximo")
            @RequestParam(required = false, defaultValue = "") String pl_max,

            @Parameter(description = "P/VP (Preço/Valor Patrimonial) mínimo")
            @RequestParam(required = false, defaultValue = "") String pvp_min,

            @Parameter(description = "P/VP (Preço/Valor Patrimonial) máximo")
            @RequestParam(required = false, defaultValue = "") String pvp_max,

            @Parameter(description = "PSR (Preço/Salário da Receita) mínimo")
            @RequestParam(required = false, defaultValue = "") String psr_min,

            @Parameter(description = "PSR (Preço/Salário da Receita) máximo")
            @RequestParam(required = false, defaultValue = "") String psr_max,

            @Parameter(description = "Dividend Yield mínimo. Medido em porcentagem use 0.01 sendo 1% a 1 sendo 100%")
            @RequestParam(required = false, defaultValue = "") String divy_min,

            @Parameter(description = "Dividend Yield máximo. Medido em porcentagem use 0.01 sendo 1% a 1 sendo 100%")
            @RequestParam(required = false, defaultValue = "") String divy_max,

            @Parameter(description = "Patrimônio Líquido Ativos mínimo")
            @RequestParam(required = false, defaultValue = "") String pativos_min,

            @Parameter(description = "Patrimônio Líquido Ativos máximo")
            @RequestParam(required = false, defaultValue = "") String pativos_max,

            @Parameter(description = "Preço/Ativo Circulante mínimo")
            @RequestParam(required = false, defaultValue = "") String pcapgiro_min,

            @Parameter(description = "Preço/Ativo Circulante máximo")
            @RequestParam(required = false, defaultValue = "") String pcapgiro_max,

            @Parameter(description = "P/EBIT (Preço/EBIT) mínimo")
            @RequestParam(required = false, defaultValue = "") String pebit_min,

            @Parameter(description = "P/EBIT (Preço/EBIT) máximo")
            @RequestParam(required = false, defaultValue = "") String pebit_max,

            @Parameter(description = "FGR (Fator de Graham) mínimo")
            @RequestParam(required = false, defaultValue = "") String fgrah_min,

            @Parameter(description = "FGR (Fator de Graham) máximo")
            @RequestParam(required = false, defaultValue = "") String fgrah_max,

            @Parameter(description = "Firma EBIT mínima. Medido em porcentagem use 0.01 sendo 1% a 1 sendo 100%")
            @RequestParam(required = false, defaultValue = "") String firma_ebit_min,

            @Parameter(description = "Firma EBIT máxima. Medido em porcentagem use 0.01 sendo 1% a 1 sendo 100%")
            @RequestParam(required = false, defaultValue = "") String firma_ebit_max,

            @Parameter(description = "Firma EBITDA mínima. Medido em porcentagem use 0.01 sendo 1% a 1 sendo 100%")
            @RequestParam(required = false, defaultValue = "") String firma_ebitda_min,

            @Parameter(description = "Firma EBITDA máxima. Medido em porcentagem use 0.01 sendo 1% a 1 sendo 100%")
            @RequestParam(required = false, defaultValue = "") String firma_ebitda_max,

            @Parameter(description = "Margem EBIT mínima. Medido em porcentagem use 0.01 sendo 1% a 1 sendo 100%")
            @RequestParam(required = false, defaultValue = "") String margemebit_min,

            @Parameter(description = "Margem EBIT máxima. Medido em porcentagem use 0.01 sendo 1% a 1 sendo 100%")
            @RequestParam(required = false, defaultValue = "") String margemebit_max,

            @Parameter(description = "Margem Líquida mínima. Medido em porcentagem use 0.01 sendo 1% a 1 sendo 100%")
            @RequestParam(required = false, defaultValue = "") String margemliq_min,

            @Parameter(description = "Margem Líquida máxima. Medido em porcentagem use 0.01 sendo 1% a 1 sendo 100%")
            @RequestParam(required = false, defaultValue = "") String margemliq_max,

            @Parameter(description = "Liquidez Corrente mínima")
            @RequestParam(required = false, defaultValue = "") String liqcorr_min,

            @Parameter(description = "Liquidez Corrente máxima")
            @RequestParam(required = false, defaultValue = "") String liqcorr_max,

            @Parameter(description = "ROIC (Retorno sobre o Capital Investido) mínimo. Medido em porcentagem use 0.01 sendo 1% a 1 sendo 100%")
            @RequestParam(required = false, defaultValue = "") String roic_min,

            @Parameter(description = "ROIC (Retorno sobre o Capital Investido) máximo. Medido em porcentagem use 0.01 sendo 1% a 1 sendo 100%")
            @RequestParam(required = false, defaultValue = "") String roic_max,

            @Parameter(description = "ROE (Retorno sobre o Patrimônio Líquido) mínimo. Medido em porcentagem use 0.01 sendo 1% a 1 sendo 100%")
            @RequestParam(required = false, defaultValue = "") String roe_min,

            @Parameter(description = "ROE (Retorno sobre o Patrimônio Líquido) máximo. Medido em porcentagem use 0.01 sendo 1% a 1 sendo 100%")
            @RequestParam(required = false, defaultValue = "") String roe_max,

            @Parameter(description = "Liquidez mínima")
            @RequestParam(required = false, defaultValue = "") String liq_min,

            @Parameter(description = "Liquidez máxima")
            @RequestParam(required = false, defaultValue = "") String liq_max,

            @Parameter(description = "Patrimônio Líquido mínimo")
            @RequestParam(required = false, defaultValue = "") String patrim_min,

            @Parameter(description = "Patrimônio Líquido máximo")
            @RequestParam(required = false, defaultValue = "") String patrim_max,

            @Parameter(description = "Dívida Bruta mínima")
            @RequestParam(required = false, defaultValue = "") String divbruta_min,

            @Parameter(description = "Dívida Bruta máxima")
            @RequestParam(required = false, defaultValue = "") String divbruta_max,

            @Parameter(description = "Taxa de Crescimento de Receita mínima. Medido em porcentagem use 0.01 sendo 1% a 1 sendo 100%")
            @RequestParam(required = false, defaultValue = "") String tx_cresc_rec_min,

            @Parameter(description = "Taxa de Crescimento de Receita máxima. Medido em porcentagem (0.01 sendo 1% a 1 sendo 100%)")
            @RequestParam(required = false, defaultValue = "") String tx_cresc_rec_max,

            @Parameter(description = "Setor. Use a chave do setor para filtrar os resultados. Acesse a documentação para ver as chaves ou use api/info/setores")
            @RequestParam(required = false, defaultValue = "") String setor,

            @Parameter(description = "Empresa negociada nos últimos 2 meses da bolsa. O parâmetro negociada deve ser ON ou OFF")
            @RequestParam(required = false, defaultValue = "ON") String negociada,

            @Parameter(description = "Ordem. Use as chaves dos indicadores para ordenar os resultados. Acesse a documentação para mais informações, use api/info/indicadores")
            @RequestParam(required = false, defaultValue = "") String ordem

    ) throws IOException {
        // Aqui você pode acessar e processar os parâmetros conforme necessário
        return filtroService.getFiltro(pl_min, pl_max, pvp_min, pvp_max, psr_min, psr_max, divy_min, divy_max, pativos_min, pativos_max, pcapgiro_min, pcapgiro_max, pebit_min, pebit_max, fgrah_min, fgrah_max, firma_ebit_min, firma_ebit_max, firma_ebitda_min, firma_ebitda_max, margemebit_min, margemebit_max, margemliq_min, margemliq_max, liqcorr_min, liqcorr_max, roic_min, roic_max, roe_min, roe_max, liq_min, liq_max, patrim_min, patrim_max, divbruta_min, divbruta_max, tx_cresc_rec_min, tx_cresc_rec_max, setor, negociada, ordem);
    }
}