# Bovespa - API

Status: Em desenvolvimento 👨🏽‍💻

A API é um serviço RESTful projetado para realizar web scraping no site fundamentus.com.br, proporcionando aos usuários acesso fácil e rápido a informações financeiras, ações e indicadores relacionados a empresas listadas na bolsa de valores.


# Tópicos

- [Endpoints](#Documentação-da-API)
  -
    - [Bovespa em geral](#-bovespa-em-geral)
      - [/api/bovespa/papeis](#-bovespa-em-geral)
      - [/api/bovespa/indicadores](#-bovespa-em-geral)
      - [/api/bovespa/empresas](#-bovespa-em-geral)
    - [Setores](#-setores)
      - [api/setor/{setor}/papeis](#-setores)
      - [api/setor/{setor}/indicadores](#-setores)
      - [api/setor/{setor}/empresas](#-setores)
    - [Ação](#-ação)
        - [api/acao/{papel}](#-ação)
        - [api/acao/{papel}/proventos](#-ação)
    - [Filtros & Pesquisa](#-filtros--pesquisa)
      - [api/filtro](#-filtros--pesquisa)
      - [api/filtro/pesquisa/{termo}](#-filtros--pesquisa)
    - [Informações](#-informações)
      - [api/info/setores](#-informações)
      - [api/info/indicadores](#-informações)
- [Chaves para usar a API](#respectivas-chaves)
  -
  - [Indicadores](#indicadores)
  - [Setores](#setores)
- [Swagger 3](#swagger-3)

- [Tecnologias utilizadas](#tecnologias-utilizadas)
- [Abrir e rodar o projeto](#abrir-e-rodar-o-projeto)

- [Autor](#autor)


# Documentação da API



<br>

## 📈 Bovespa em geral
Retorna todos os papeis da Bovespa sem filtros

<br> 

```
  GET /api/bovespa/papeis
```
Retorna uma lista contendo papeis

Exemplo:
`[
"MGLU3",
"LJQQ3",
"ESPA3",
"GUAR3",
"BHIA3",
"AMAR3",
"AMER3",
"EPAR3",
"CGRA3",
"CGRA4",
"WLMM3",
"VSTE3",
"ALLD3",
"WLMM4",
"LREN3",
"SBFG3",
"ARZZ3",
"SOMA3",
"PETZ3",
"CEAB3", ...
]`

<br>

```
  GET /api/bovespa/indicadores
```
Retorna uma lista contendo indicadores

Exemplo:
`j
[
  {
        "Papel": "PETR3",
        "Empresa": "PETROBRAS",
        "Cotação": "42,33",
        "P/L": "4,03",
        "PV/P": "1,43",
        "PSR": "1,030",
        "Div.Yield": "17,12%",
        "P/Ativo": "0,538",
        "P/Cap.Giro": "-76,06",
        "P/EBIT": "2,29",
        "P/Ativo Circ.Liq": "-1,13",
        "EV/EBIT": "3,28",
        "EV/EBITDA": "2,50",
        "Mrg Ebit": "44,92%",
        "Mrg. Líq.": "25,64%",
        "Liq. Corr.": "0,95",
        "ROIC": "25,76%",
        "ROE": "35,47%",
        "Liq.2meses": "377.828.000,00",
        "Patrim. Líq": "386.007.000.000,00",
        "Dív.Brut/ Patrim.": "0,79",
        "Cresc. Rec.5a": "20,27%"
  },
  ...
]
`

<br>

```
  GET /api/bovespa/empresas
```
Retorna uma lista contendo nomes de empresas 

Exemplo:
`["MITRE REALTY","BIOMM SA","INFRACOMM","BANCO DO NORDESTE","MOBLY","AZEVEDO & TRAVASSOS","Direcional Engenharia","Marfrig","WLM","SLC Agricola","BANRISUL S/A","SANEPAR","VESTE","MONARK","QUALICORP","LOCALIZA RENT A CAR","B3","KORA SAUDE","GERDAU S.A.","PANVEL FARMÁCIAS","SYN PROP TEC","FICA","BOMBRIL SA","BAHEMA","MELNICK","ORIZON","PLASCAR PARTICIPAÇÕES INDUSTRIAIS S.A","POSITIVO, ...]`
<br>
<br>
---
<br>

## 💼 Setores
Retorna todos os papeis da Bovespa usando as [chaves dos setores](#setores)

<br> 

```
  GET /api/setor/{setor}/papeis
```
Retorna uma lista contendo papeis

Exemplo: <br>
📌 /api/setor/12/papeis:
`[
"MOVI3",
"ANIM3",
"COGN3",
"SEER3",
"BAHI3",
"DOTZ3",
"MILS3",
"VAMO3",
"CSED3",
"ARML3",
"YDUQ3",
"RENT3"
]`

<br>

```
  GET /api/setor/12/indicadores
```
Retorna uma lista contendo indicadores

Exemplo:
<br>
📌 /api/setor/12/indicadores:
`
[
{
"Papel": "MOVI3",
"Empresa": "MOVIDA",
"Cotação": "8,30",
"P/L": "-67,26",
"PV/P": "0,99",
"PSR": "0,285",
"Div.Yield": "1,46%",
"P/Ativo": "0,138",
"P/Cap.Giro": "3,61",
"P/EBIT": "1,35",
"P/Ativo Circ.Liq": "-0,22",
"EV/EBIT": "6,60",
"EV/EBITDA": "3,86",
"Mrg Ebit": "21,06%",
"Mrg. Líq.": "-0,42%",
"Liq. Corr.": "1,21",
"ROIC": "12,65%",
"ROE": "-1,48%",
"Liq.2meses": "51.856.800,00",
"Patrim. Líq": "3.027.690.000,00",
"Dív.Brut/ Patrim.": "4,52",
"Cresc. Rec.5a": "34,47%"
},
...
]
`

<br>

```
  GET /api/setor/12/empresas
```
Exemplo:
<br>
📌 /api/setor/12/empresas:
`[
"MOVIDA",
"ARMAC",
"BAHEMA",
"ANIMA",
"MILLS",
"LOCALIZA RENT A CAR",
"SER EDUCA",
"DOTZ SA",
"CRUZEIRO EDU",
"COGNA",
"YDUQS PART",
"VAMOS"
]`


<br>
<br>

---
<br>

<br>

## 🔐 Ação
Retorna um único papel dentro de todos da Bovespa

<br> 

```
  GET /api/acao/{papel}
```
Retorna uma ação com todos os indicadores

Exemplo: <br>
📌 /api/acao/petr4:
`[
{
"Papel": "PETR4",
"Empresa": "PETROBRAS",
"Cotação": "41,30",
"P/L": "3,94",
"PV/P": "1,40",
"PSR": "1,005",
"Div.Yield": "17,55%",
"P/Ativo": "0,525",
"P/Cap.Giro": "-74,21",
"P/EBIT": "2,24",
"P/Ativo Circ.Liq": "-1,10",
"EV/EBIT": "3,23",
"EV/EBITDA": "2,46",
"Mrg Ebit": "44,92%",
"Mrg. Líq.": "25,64%",
"Liq. Corr.": "0,95",
"ROIC": "25,76%",
"ROE": "35,47%",
"Liq.2meses": "1.296.950.000,00",
"Patrim. Líq": "386.007.000.000,00",
"Dív.Brut/ Patrim.": "0,79",
"Cresc. Rec.5a": "20,27%"
}
]`

<br>

```
  GET /api/acao/{papel}/proventos
```
Retorna um Json contenda proventos da ação

Exemplo:
<br>
📌 /api/acao/petr4/proventos:
`
[
{
"Valor": "0,6722",
"Tipo": "DIVIDENDO",
"Data do anuncio": "21/11/2023",
"Data de pagamento": "20/02/2024",
"Por quantas ações": "1"
},
{
"Valor": "0,4294",
"Tipo": "JRS CAP PROPRIO",
"Data do anuncio": "21/11/2023",
"Data de pagamento": "20/02/2024",
"Por quantas ações": "1"
},
{
"Valor": "0,2431",
"Tipo": "DIVIDENDO",
"Data do anuncio": "21/11/2023",
"Data de pagamento": "20/02/2024",
"Por quantas ações": "1"
}, ...
`

<br>
<br>

---
<br>
<br>

## 🔎 Filtros & Pesquisa
Retorna todos os papeis da Bovespa usando as [chaves dos indicadores](#indicadores)<br><br>

Alguns filtros são medidos por porcentagem, então para usar esses filtros, use o ponto (.) ao invés da vírgula (,) e 1 equivale a 100% e 0.1 a 10%

| Indicador                                  | Uso |
|:-------------------------------------------| :---- |
| `(DY) Dividend Yield`                      |  `%`  |
| `Firma EBIT`                               |  `%`  |
| `Margem EBIT`                              |  `%`  |
| `Firma EBITA`                              |  `%`  |
| `Margem Líquida`                           |  `%`  |
| `(ROIC) Retorno sobre o Capital Investido` |  `%`  |
| `(ROE) Retorno sobre o Patrimônio Líquido` |  `%`  |
| `Taxa de Crescimento de Receita`           |  `%`  |

<br> 

```
  GET /api/filtro
```
Retorna todos os papeis dentro dos filtros estabelecidos

Exemplo: <br>
📌 /api/filtro?pvp_max=1&divy_min=0.1:
`[
{
"Papel": "GOAU4",
"Empresa": "METALÚRGICA GERDAU",
"Cotação": "9,88",
"P/L": "3,72",
"PV/P": "0,57",
"PSR": "0,141",
"Div.Yield": "16,11%",
"P/Ativo": "0,132",
"P/Cap.Giro": "0,48",
"P/EBIT": "0,99",
"P/Ativo Circ.Liq": "1,63",
"EV/EBIT": "1,42",
"EV/EBITDA": "1,10",
"Mrg Ebit": "14,34%",
"Mrg. Líq.": "11,35%",
"Liq. Corr.": "3,02",
"ROIC": "15,99%",
"ROE": "15,29%",
"Liq.2meses": "89.946.100,00",
"Patrim. Líq": "17.948.100.000,00",
"Dív.Brut/ Patrim.": "0,64",
"Cresc. Rec.5a": "20,96%"
},
{
"Papel": "MTRE3",
"Empresa": "MITRE REALTY",
"Cotação": "4,80",
"P/L": "5,88",
"PV/P": "0,52",
"PSR": "0,557",
"Div.Yield": "15,82%",
"P/Ativo": "0,234",
"P/Cap.Giro": "0,45",
"P/EBIT": "9,80",
"P/Ativo Circ.Liq": "0,79",
"EV/EBIT": "19,89",
"EV/EBITDA": "17,30",
"Mrg Ebit": "5,68%",
"Mrg. Líq.": "9,43%",
"Liq. Corr.": "2,65",
"ROIC": "2,57%",
"ROE": "8,85%",
"Liq.2meses": "11.271.300,00",
"Patrim. Líq": "976.846.000,00",
"Dív.Brut/ Patrim.": "0,63",
"Cresc. Rec.5a": "32,44%"
}, ...]
`

<br>

```
  GET /api/filtro/pesquisa/{termo}
```
Retorna um Json contendo papeis com o termo pesquisado por ordem de similaridade

Exemplo:
<br>
📌 /api/filtro/pesquisa/itau:
`
[
{
"Papel": "ITUB4",
"Empresa": "ITAUUNIBANCO",
"Cotação": "34,29",
"P/L": "10,07",
"P/VP": "1,84",
"PSR": "0,000",
"Div.Yield": "3,61%",
"P/Ativo": "0,000",
"P/Cap.Giro": "0,00",
"P/EBIT": "0,00",
"P/Ativo Circ.Liq": "0,00",
"EV/EBIT": "0,00",
"EV/EBITDA": "0,00",
"Mrg Ebit": "0,00%",
"Mrg. Líq.": "0,00%",
"Liq. Corr.": "0,00",
"ROIC": "0,00%",
"ROE": "18,29%",
"Liq.2meses": "835.073.000,00",
"Patrim. Líq": "182.505.000.000,00",
"Dív.Brut/ Patrim.": "0,00",
"Cresc. Rec.5a": "50,81%"
},
{
"Papel": "ITSA3",
"Empresa": "ITAÚSA",
"Cotação": "10,43",
"P/L": "7,80",
"P/VP": "1,35",
"PSR": "14,528",
"Div.Yield": "5,27%",
"P/Ativo": "1,048",
"P/Cap.Giro": "21,63",
"P/EBIT": "118,64",
"P/Ativo Circ.Liq": "-17,06",
"EV/EBIT": "121,75",
"EV/EBITDA": "56,98",
"Mrg Ebit": "12,25%",
"Mrg. Líq.": "193,30%",
"Liq. Corr.": "1,65",
"ROIC": "0,97%",
"ROE": "17,31%",
"Liq.2meses": "2.080.300,00",
"Patrim. Líq": "79.738.000.000,00",
"Dív.Brut/ Patrim.": "0,14",
"Cresc. Rec.5a": "14,42%"
}, ...]
`

<br>
<br>

---
<br>
<br>

## 🔎 Informações
Retorna as [chaves](#respectivas-chaves) dos [indicadores](#indicadores) e dos [setores](#setores)<br>
```
  GET /api/info/setores
```
```
  GET /api/info/indicadores
```
📌 O retorno é igual ao [Respectivas chaves](#respectivas-chaves) e pode auxiliar na hora de usar a API no FrontEnd.

<br>
<br>

---
<br>
<br>

## Respectivas chaves

### Indicadores

| Indicador                                | Chave |
| :----------------------------------------| :---- |
| `P/L`                                    |  `1`  |
| `P/VP`                                   |  `2`  |
| `PSR`                                    |  `3`  |
| `Dividend Yield`                         |  `4`  |
| `P/Ativos`                               |  `5`  |
| `P/Cap. Giro`                            |  `6`  |
| `P/Ativo Circ. Liq.`                     |  `7`  |
| `P/EBIT`                                 |  `8`  |
| `EV/EBIT`                                |  `9`  |
| `EV/EBITDA`                              |  `10` |
| `Margem EBIT`                            |  `11` |
| `Margem Líquida`                         |  `12` |
| `Liquidez Corrente`                      |  `13` |
| `ROIC`                                   |  `14` |
| `ROE`                                    |  `15` |
| `Liquidez das Ações`                     |  `16` |
| `Patrimônio Líquido`                     |  `17` |
| `Dív. Bruta sobre o Patrimônio Líquido`  |  `18` |
| `Tx. de crescimento da Receita Líq`      |  `19` |


<br>

### Setores

| Setor                                       | Chave |
| --------------------------------------------| ----- |
| `Agropecuária`                            | `1` |
| `Água e Saneamento`                       | `2` |
| `Alimentos Processados`                   | `3` |
| `Serv.Méd.Hospit. Análises e Diagnósticos`| `4` |
| `Automóveis e Motocicletas`               | `5` |
| `Bebidas`                                 | `6` |
| `Comércio`                                | `7` |
| `Comércio e Distribuição`                 | `8` |
| `Computadores e Equipamentos`             | `9` |
| `Construção Civil`                        | `10`|
| `Construção e Engenharia`                 | `11`|
| `Diversos`                                | `12`|
| `Embalagens`                              | `13`|
| `Energia Elétrica`                        | `14`|
| `Equipamentos`                            | `15`|
| `Exploração de Imóveis`                   | `16`|
| `Gás`                                     | `17`|
| `Holdings Diversificadas`                 | `18`|
| `Hoteis e Restaurantes`                   | `19`|
| `Intermediários Financeiros`              | `20`|
| `Madeira e Papel`                         | `21`|
| `Máquinas e Equipamentos`                 | `22`|
| `Materiais Diversos`                      | `23`|
| `Material de Transporte`                  | `24`|
| `Medicamentos e Outros Produtos`          | `25`|
| `Mídia`                                   | `26`|
| `Mineração`                               | `27`|
| `Outros`                                  | `28`|
| `Outros Títulos`                          | `29`|
| `Petróleo, Gás e Biocombustíveis`         | `30`|
| `Previdência e Seguros`                   | `31`|
| `Produtos de Uso Pessoal e de Limpeza`    | `32`|
| `Programas e Serviços`                    | `33`|
| `Químicos`                                | `34`|
| `Securitizadoras de Recebíveis`           | `35`|
| `Serviços Diversos`                       | `36`|
| `Serviços Financeiros Diversos`           | `37`|
| `Siderurgia e Metalurgia`                 | `38`|
| `Tecidos, Vestuário e Calçados`           | `39`|
| `Telecomunicações`                        | `40`|
| `Transporte`                              | `41`|
| `Utilidades Domésticas`                   | `42`|
| `Viagens e Lazer`                         | `43`|

<br>

---

<br>

## Swagger 3
Para usar o Swagger utilize no navegador o seguinte link:


```
http://localhost:8080/swagger-ui/index.html
```

<br>

---

<br>

## Tecnologias utilizadas
- Java 17
- Spring Boot
- Swagger 3
- J Unit
- Jsoup
- Maven

<br>

---

## Abrir e rodar o projeto

1. Para rodar o projeto ultilize o git clone com a url:<br>https://github.com/Rivas12/Bovespa-API.git

2. Abra sua IDE (ultilizei o IntelliJ IDE).

3. Selecione "File" > "Open..." e navegue até o diretório onde você clonou o projeto

4. Certifique-se de que a IDE reconheceu o projeto como um projeto Maven. Se necessário, clique com o botão direito no arquivo pom.xml e escolha "Add as Maven Project"

5. Na IDE, navegue até o arquivo src/main/java/com/bovespaApi/BovespaApiApplication.java

6. Clique com o botão direito no arquivo e escolha "Run 'BovespaApiApplication'"

7. O aplicativo será iniciado e estará disponível em `http://localhost:8080`

<br>

---

## Autor

[@Rivaldo Silveira - Programador](https://github.com/Rivas12)

