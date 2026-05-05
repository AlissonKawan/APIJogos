package br.com.alisson.resource;

import br.com.alisson.dto.RawGameResponseDTO;
import br.com.alisson.entity.Game;
import br.com.alisson.repository.GameRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import br.com.alisson.service.GameService;
import java.util.List;

/*
    Preciso usar dois paths, um é para endereço padrão da classe, outro é para endereço do método.
    /games + /search = /games/search
 */

// Basicamente @Path diz: tudo que começar com /games vai cair nessa classe.
@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameResource {

    // Injeção de dependência: Quarkus me dá uma instância pronta da classe GameService.
    // Então não preciso fazer: new GameService()
    @Inject
    GameService gameService;

    @Inject
    GameRepository repository;

    // @GET é o método/tipo da requisição. GET, nesse caso, serve para buscar dados.
    @GET

    // /search define que esse método responde quando alguém chamar /games/search.
    @Path("/search")

    // @QueryParam pega o valor do parâmetro name da URL e coloca na variável 'nome'.
    // Exemplo: http://localhost:8080/games/search?name=god
    public RawGameResponseDTO buscar(@QueryParam("name") String nome) {return (RawGameResponseDTO) gameService.buscarJogos(nome);}


    // Resumindo acima: esse método responde quando alguém chamar GET /games/search?name=algumJogo

    // ── ADICIONADO ──────────────────────────────────────────

    // LISTAR TODOS os jogos salvos no banco local
    @GET
    public List<Game> list() {
        return repository.listAll();
    }

    // INSERIR um jogo no banco local
    @POST
    @Transactional
    public void add(Game game) {
        repository.persist(game);
    }

    // ────────────────────────────────────────────────────────
}
