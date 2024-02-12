# Bovespa - API

Status: Em desenvolvimento 👨🏽‍💻

A API é um serviço RESTful projetado para realizar web scraping no site fundamentus.com.br, proporcionando aos usuários acesso fácil e rápido a informações financeiras, ações e indicadores relacionados a empresas listadas na bolsa de valores.


# Tópicos

- [Endpoints](#Documentação-da-API)
  -
    - [Retornam List](#retorna-listas-contendo-papeis)
    - [Retornam Json](#retorna-um-json-contendo-indicadores)
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

## Retorna Listas contendo papeis



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
#### Retorna papeis
```
  GET acoes/acoes/papeis/lista
```

<br>

#### Retorna papeis por setor
```
  GET acoes/papeis/setor/{setor}/lista
```

<br>
<br>
<br>

## Retorna um Json contendo indicadores
Exemplo:
```
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
```

<br>

#### Todos os papeis
```
  GET acoes/acoes/papeis
```

<br>

#### Todos os papeis por setor

```
  GET acoes/papeis/setor/{setor}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `setor`      | `string` | **Obrigatório**. O setor que você quer |

<br>

#### Todos os papeis ordenadados por algum indicador ("P/L", "P/VP", "DY", ...) na ordem crescente ou decrescente



```
  GET acoes/papeis/ordernar_por/{chave}/{cresc_desc}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `chave`      | `string` | **Obrigatório**. O setor que você quer |
| `cresc_desc` | `string` | **Obrigatório**. crescente(cresc) ou decrescente(desc) |

<br>

#### Todos os papeis parecidos com o termo pesquisado, e ordenados por similaridade

```
  GET acoes/papeis/pesquisa/{termo}
```

| Parâmetro   | Tipo       | Descrição                                           |
| :---------- | :--------- | :-------------------------------------------------  |
| `termo`      | `string`  | **Obrigatório**. Nome da empresa que quer pesquisar |

<br>

#### Retorna um único papel com indicadores

```
  GET acoes/papel/{papel}
```

| Parâmetro   | Tipo       | Descrição                                           |
| :---------- | :--------- | :-------------------------------------------------  |
| `papel`     | `string`  | **Obrigatório**. Papel da empresa que quer buscar    |

<br>
<br>

---

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
Para usar o Swagger utilize:


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

