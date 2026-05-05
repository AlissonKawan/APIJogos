// Define o pacote onde essa classe está localizada
package br.com.alisson.resource;

// Importa o DTO que representa a resposta da API externa RAWG
import br.com.alisson.dto.RawGameResponseDTO;

// Importa a entidade Game para ser usada nos novos endpoints do banco local
import br.com.alisson.entity.Game;

// Importa o repositório que acessa o banco de dados
import br.com.alisson.repository.GameRepository;

// Importa a anotação que garante que operações de escrita no banco sejam feitas dentro de uma transação
// Sem isso, o Hibernate não consegue persistir (salvar) dados
import jakarta.transaction.Transactional;

// Importa todas as anotações JAX-RS de uma vez: @GET, @POST, @PUT, @DELETE, @Path, @QueryParam, etc.
import jakarta.ws.rs.*;

// Importa o MediaType para definir o formato de entrada e saída (JSON, XML, etc.)
import jakarta.ws.rs.core.MediaType;

// Importa a anotação de injeção de dependência
import jakarta.inject.Inject;

// Importa o serviço que faz a comunicação com a API externa RAWG
import br.com.alisson.service.GameService;

// Importa a classe List para retornar listas de jogos
import java.util.List;

/*
    Preciso usar dois paths, um é para endereço padrão da classe, outro é para endereço do método.
    /games + /search = /games/search
 */

// Basicamente @Path diz: tudo que começar com /games vai cair nessa classe.
@Path("/games")

// @Produces diz que todos os métodos dessa classe retornam JSON por padrão
@Produces(MediaType.APPLICATION_JSON)

// @Consumes diz que todos os métodos dessa classe esperam receber JSON no corpo da requisição
@Consumes(MediaType.APPLICATION_JSON)
public class GameResource {

    // Injeção de dependência: Quarkus me dá uma instância pronta da classe GameService.
    // Então não preciso fazer: new GameService()
    @Inject
    GameService gameService;

    // Injeta o repositório que acessa o banco de dados
    // O Quarkus cria e gerencia essa instância automaticamente
    @Inject
    GameRepository repository;

    // @GET é o método/tipo da requisição. GET, nesse caso, serve para buscar dados.
    @GET

    // /search define que esse método responde quando alguém chamar /games/search.
    @Path("/search")

    // @QueryParam pega o valor do parâmetro name da URL e coloca na variável 'nome'.
    // Exemplo: http://localhost:8080/games/search?name=god
    public RawGameResponseDTO buscar(@QueryParam("name") String nome) {
        return (RawGameResponseDTO) gameService.buscarJogos(nome);
    }

    // Resumindo acima: esse método responde quando alguém chamar GET /games/search?name=algumJogo

    // Responde quando alguém chamar GET /games (sem parâmetros)
    // Retorna uma lista com todos os jogos salvos no banco de dados local
    @GET
    public List<Game> list() {
        // listAll() é um método herdado do PanacheRepository
        // Ele executa um SELECT * FROM game e retorna todos os registros
        return repository.listAll();
    }

    // Responde quando alguém chamar POST /games com um JSON no corpo da requisição
    // Serve para inserir um novo jogo no banco de dados local
    @POST
    // @Transactional garante que a operação de salvar seja feita dentro de uma transação do banco
    // Se algo der errado, o Hibernate faz rollback automaticamente (desfaz a operação)
    @Transactional
    public void add(Game game) {
        // persist() é um método herdado do PanacheRepository
        // Ele executa um INSERT no banco com os dados do objeto Game recebido
        repository.persist(game);
    }
}