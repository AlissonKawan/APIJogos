package br.com.alisson.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.inject.Inject;
import br.com.alisson.service.GameService;

//basicamente Path diz: tudo que começar com /games vai cair nessa classe
@Path("/games")
public class GameResource {

    //injeção de dependencia : Quarkus me da uma instância pronta da classe de service GameService
    // entçao não preciso fazer : new GameService()
    @Inject
    GameService gameService;

    //get é o metodo/tipo da requisição que quero get no caso seria buscar dados
    @GET
    // search é para definir: esse metodo responde quando alguem chamar por /games/search
    // QueryParam : pega valor da URL: exemplo: http://localhost:8080/games/search?name=god
    @Path("/search")
    public String buscar(@QueryParam("name") String nome) {
        return gameService.buscarJogos(nome);
    }
    //resumindo acima: esse metodo responde quando alguem chamar por get
}