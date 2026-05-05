package br.com.alisson.resource;

import br.com.alisson.dto.GameResponseDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.inject.Inject;
import br.com.alisson.service.GameService;

import java.util.List;

/*
    Preciso usar dois paths, um é para endereço padrão da classe, outro é para endereço do método.
    /games + /search = /games/search
 */

// Basicamente @Path diz: tudo que começar com /games vai cair nessa classe.
@Path("/games")
public class GameResource {

    // Injeção de dependência: Quarkus me dá uma instância pronta da classe GameService.
    // Então não preciso fazer: new GameService()
    @Inject
    GameService gameService;

    // @GET é o método/tipo da requisição. GET, nesse caso, serve para buscar dados.
    @GET

    // /search define que esse método responde quando alguém chamar /games/search.
    @Path("/search")

    // @QueryParam pega o valor do parâmetro name da URL e coloca na variável 'nome'.
    // Exemplo: http://localhost:8080/games/search?name=god
    public List<GameResponseDTO> buscar(@QueryParam("name") String nome) {
        return gameService.buscarJogos(nome);
    }

    // Resumindo acima: esse método responde quando alguém chamar GET /games/search?name=algumJogo
}