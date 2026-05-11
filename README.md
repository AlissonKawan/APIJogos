# API de Jogos - RAWG

Projeto desenvolvido em Java com Quarkus para consumo da API externa RAWG, tratamento dos dados recebidos e disponibilização desses dados por meio de endpoints REST.

O sistema permite buscar jogos pelo nome, consultar informações relevantes vindas da API externa e estruturar essas informações de forma mais simples para o usuário final. O projeto também foi organizado seguindo separação de responsabilidades em camadas, com Resource, Service, Client, DTOs e camada de persistência.

---

## 📌 Objetivo do Projeto

O objetivo deste projeto é desenvolver uma aplicação Java capaz de:

- Consumir uma API externa;
- Criar endpoints próprios;
- Coletar dados de jogos;
- Tratar e organizar os dados recebidos;
- Apresentar as informações de forma clara ao usuário;
- Realizar persistência e consulta em banco de dados;
- Aplicar arquitetura em camadas.

Este projeto foi desenvolvido como parte do Checkpoint 5 da disciplina de Domain Driven Design.

---

## 🎮 API Externa Utilizada

A API externa utilizada no projeto foi a **RAWG Video Games Database API**.

A RAWG é uma API pública que fornece dados sobre jogos, incluindo:

- Nome do jogo;
- Nota/rating;
- Data de lançamento;
- Imagem de fundo;
- Identificador do jogo;
- Plataformas;
- Gêneros;
- Outras informações relacionadas.

Neste projeto, os dados consumidos da RAWG são tratados e convertidos para DTOs próprios antes de serem retornados pela API interna.

---

## 🛠️ Tecnologias Utilizadas

- Java
- Quarkus
- Jakarta REST / JAX-RS
- MicroProfile REST Client
- Hibernate ORM / Panache
- Banco de dados relacional
- Maven
- JSON
- Postman ou navegador para testes dos endpoints

---

## 📁 Estrutura do Projeto

A estrutura principal do projeto segue uma arquitetura em camadas:

```text
src/main/java/br/com/alisson/
│
├── client/
│   └── RawgClient.java
│
├── dto/
│   ├── RawGameDTO.java
│   ├── RawGameResponseDTO.java
│   └── GameResponseDTO.java
│
├── entity/
│   └── Game.java
│
├── repository/
│   └── GameRepository.java
│
├── resource/
│   └── GameResource.java
│
└── service/
    └── GameService.java
🧱 Arquitetura em Camadas
Resource

A camada Resource é responsável por expor os endpoints da aplicação.

Ela recebe as requisições HTTP, captura os parâmetros enviados pelo usuário e chama a camada de serviço.

Exemplo:

@Path("/games")
public class GameResource {
    // endpoints da API
}
Service

A camada Service contém a regra de negócio da aplicação.

Ela é responsável por:

Chamar o client da API externa;
Tratar os dados recebidos;
Converter DTOs externos em DTOs internos;
Coordenar operações de persistência;
Retornar os dados já organizados para a Resource.

Exemplo de responsabilidade:

public List<GameResponseDTO> buscarJogos(String nome) {
    // chama API externa
    // trata os dados
    // retorna resposta organizada
}
Client

A camada Client é responsável pela comunicação com a API externa RAWG.

Ela utiliza o MicroProfile REST Client para realizar as requisições HTTP.

Exemplo:

@RegisterRestClient
public interface RawgClient {
    // chamada para a API RAWG
}
DTO

Os DTOs são usados para transportar dados entre as camadas da aplicação.

Eles evitam que a aplicação exponha diretamente a estrutura completa da API externa ou da entidade do banco.

Principais DTOs:

RawGameDTO: representa os dados brutos recebidos da RAWG;
RawGameResponseDTO: representa a resposta completa da RAWG;
GameResponseDTO: representa os dados tratados que serão retornados ao usuário.
Entity

A camada Entity representa a tabela do banco de dados.

Exemplo:

@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    private Long rawgId;
    private String name;
    private Double rating;
    private String released;
    private String backgroundImage;
}
Repository

A camada Repository é responsável pelas operações com o banco de dados.

Ela centraliza consultas, persistência e manipulação dos dados salvos.

Exemplo:

@ApplicationScoped
public class GameRepository implements PanacheRepository<Game> {
}
🔗 Endpoints da API
Buscar jogos pelo nome
GET /games/search?name={nome}
Exemplo de requisição
GET http://localhost:8080/games/search?name=god
Exemplo de resposta
[
  {
    "id": 3498,
    "name": "Grand Theft Auto V",
    "rating": 4.47,
    "released": "2013-09-17",
    "backgroundImage": "https://media.rawg.io/media/games/example.jpg"
  }
]
Salvar jogo no banco de dados
POST /games
Exemplo de requisição
{
  "rawgId": 3498,
  "name": "Grand Theft Auto V",
  "rating": 4.47,
  "released": "2013-09-17",
  "backgroundImage": "https://media.rawg.io/media/games/example.jpg"
}
Exemplo de resposta
{
  "id": 1,
  "rawgId": 3498,
  "name": "Grand Theft Auto V",
  "rating": 4.47,
  "released": "2013-09-17",
  "backgroundImage": "https://media.rawg.io/media/games/example.jpg"
}
Listar jogos salvos
GET /games
Exemplo de resposta
[
  {
    "id": 1,
    "rawgId": 3498,
    "name": "Grand Theft Auto V",
    "rating": 4.47,
    "released": "2013-09-17",
    "backgroundImage": "https://media.rawg.io/media/games/example.jpg"
  }
]
Buscar jogo salvo por ID
GET /games/{id}
Exemplo
GET http://localhost:8080/games/1
Remover jogo salvo
DELETE /games/{id}
Exemplo
DELETE http://localhost:8080/games/1
⚙️ Configuração do Projeto

As configurações da aplicação ficam no arquivo:

src/main/resources/application.properties

Exemplo de configuração:

# Porta da aplicação
quarkus.http.port=8080

# Configuração da API RAWG
rawg.api.key=SUA_CHAVE_DA_API_AQUI

# URL base da API RAWG
quarkus.rest-client.rawg-api.url=https://api.rawg.io/api

# Banco de dados
quarkus.datasource.db-kind=h2
quarkus.datasource.jdbc.url=jdbc:h2:mem:gamesdb
quarkus.datasource.username=sa
quarkus.datasource.password=

# Hibernate
quarkus.hibernate-orm.database.generation=drop-and-create
quarkus.hibernate-orm.log.sql=true

Importante: a chave da API não deve ser enviada publicamente em repositórios abertos. O ideal é utilizar variável de ambiente ou manter o repositório privado.

▶️ Como Executar o Projeto
1. Clonar o repositório
git clone URL_DO_REPOSITORIO
2. Entrar na pasta do projeto
cd nome-do-projeto
3. Configurar a chave da API

No arquivo application.properties, configure:

rawg.api.key=SUA_CHAVE_DA_RAWG
4. Executar o projeto em modo desenvolvimento
./mvnw quarkus:dev

No Windows:

mvnw.cmd quarkus:dev
5. Acessar a aplicação
http://localhost:8080
🧪 Como Testar

O teste dos endpoints pode ser feito pelo navegador, Postman ou Insomnia.

Buscar jogos
GET http://localhost:8080/games/search?name=far%20cry
Listar jogos salvos
GET http://localhost:8080/games
Salvar jogo
POST http://localhost:8080/games

Body:

{
  "rawgId": 123,
  "name": "Exemplo de Jogo",
  "rating": 4.5,
  "released": "2020-01-01",
  "backgroundImage": "https://exemplo.com/imagem.jpg"
}
🔄 Fluxo de Funcionamento

O fluxo principal da aplicação funciona da seguinte forma:

O usuário faz uma requisição para o endpoint /games/search;
A GameResource recebe a requisição;
A GameResource chama a GameService;
A GameService chama o RawgClient;
O RawgClient acessa a API externa RAWG;
A RAWG retorna uma lista de jogos;
A GameService trata os dados recebidos;
Os dados são convertidos para GameResponseDTO;
A resposta é retornada ao usuário em formato JSON.

Fluxo resumido:

Usuário
  ↓
GameResource
  ↓
GameService
  ↓
RawgClient
  ↓
API RAWG
  ↓
GameService
  ↓
GameResponseDTO
  ↓
Usuário
🧠 Tratamento dos Dados

A API RAWG retorna muitos dados que nem sempre são necessários para o usuário.

Por isso, o projeto realiza o tratamento dos dados e retorna apenas as informações mais importantes:

ID do jogo;
Nome;
Avaliação;
Data de lançamento;
Imagem de fundo.

Isso deixa a resposta mais limpa, organizada e fácil de consumir.

Exemplo de dado vindo da RAWG:

{
  "id": 3498,
  "name": "Grand Theft Auto V",
  "rating": 4.47,
  "released": "2013-09-17",
  "background_image": "https://media.rawg.io/media/games/example.jpg"
}

Exemplo de dado tratado pela aplicação:

{
  "id": 3498,
  "name": "Grand Theft Auto V",
  "rating": 4.47,
  "released": "2013-09-17",
  "backgroundImage": "https://media.rawg.io/media/games/example.jpg"
}
🗄️ Persistência em Banco de Dados

O projeto possui persistência em banco de dados para armazenar jogos consultados ou selecionados pelo usuário.

A entidade principal é Game.

Tabela esperada:

GAME

Campos principais:

Campo	Tipo	Descrição
id	Long	Identificador interno no banco
rawgId	Long	ID original do jogo na API RAWG
name	String	Nome do jogo
rating	Double	Avaliação do jogo
released	String	Data de lançamento
backgroundImage	String	URL da imagem do jogo

A persistência permite que a aplicação não dependa apenas da API externa para consultar dados já salvos.

✅ Funcionalidades Implementadas
Consumo da API externa RAWG;
Endpoint para busca de jogos;
Tratamento dos dados recebidos;
Conversão de dados externos para DTO interno;
Organização do projeto em camadas;
Persistência de jogos no banco de dados;
Consulta de jogos salvos;
Remoção de jogos salvos;
Retorno das informações em JSON.
📌 Regras de Negócio
O usuário pode buscar jogos informando parte do nome;
A aplicação consulta a API externa RAWG;
Os dados retornados são tratados antes de serem exibidos;
Apenas informações relevantes são retornadas;
Jogos podem ser persistidos no banco de dados;
Jogos salvos podem ser consultados posteriormente.
🚨 Possíveis Erros
API Key inválida

Caso a chave da RAWG esteja incorreta, a API externa pode retornar erro de autorização.

Solução:

rawg.api.key=SUA_CHAVE_CORRETA
Porta 8080 em uso

Caso a porta 8080 já esteja sendo usada, o Quarkus pode não iniciar corretamente.

Solução:

quarkus.http.port=8081

Ou finalizar o processo que está usando a porta 8080.

Nome não informado na busca

Se o parâmetro name não for enviado, a aplicação pode retornar erro ou lista vazia.

Exemplo incorreto:

GET /games/search

Exemplo correto:

GET /games/search?name=god
📚 Conceitos Aplicados
API REST;
Consumo de API externa;
DTO;
Entity;
Repository;
Service;
Resource;
Injeção de dependência;
Persistência em banco de dados;
Arquitetura em camadas;
JSON;
HTTP GET, POST e DELETE.
👨‍💻 Autor

Projeto desenvolvido por Alisson Kawan.

Curso: Análise e Desenvolvimento de Sistemas
Disciplina: Domain Driven Design
Professor: Fernando Almeida

📄 Licença

Este projeto foi desenvolvido para fins acadêmicos.


Só uma coisa importante: se no teu código **ainda não tiver** `POST /games`, `GET /games`, `GET /games/{id}` e `DELETE /games/{id}`, não mete isso no README como se estivesse pronto, porque aí vira mentira bonita — e professor percebe essa merda fácil. Nesse caso, deixa só o `/games/search` ou coloca como “funcionalidades previstas”.
