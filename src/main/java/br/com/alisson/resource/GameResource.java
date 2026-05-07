package br.com.alisson.resource;

import br.com.alisson.dto.GameResponseDTO;
import br.com.alisson.entity.GameEntity;
import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import br.com.alisson.service.GameService;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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

    // @GET é o método/tipo da requisição. GET, nesse caso, serve para buscar dados.
    @GET

    // /search define que esse método responde quando alguém chamar /games/search.
    @Path("/search")

    // @QueryParam pega o valor do parâmetro name da URL e coloca na variável 'nome'.
    // Exemplo: http://localhost:8080/games/search?name=god
    public Response buscar(@QueryParam("name") String nome) {

        if (nome == null || nome.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("O parâmetro 'name' é obrigatório.")
                    .build();
        }

        List<GameResponseDTO> jogos = gameService.buscarJogos(nome);

        return Response.ok(jogos).build();
    }

    // Resumindo acima: esse método responde quando alguém chamar GET /games/search?name=algumJogo

    @POST
    public Response salvarJogo(GameEntity game) {
        GameEntity jogoSalvo = gameService.salvarJogo(game);
        return Response.status(Response.Status.CREATED)
                .entity(jogoSalvo)
                .build();
    }

    @GET
    @Path("/salvos")
    public Response listarJogosSalvos() {
        List<GameEntity> jogos = gameService.listarJogosSalvos();
        return Response.ok(jogos).build();
    }

    @GET
    @Path("/salvos/{id}")
    public Response buscarJogoPorId(@PathParam("id") Long id) {
        GameEntity jogo = gameService.buscarJogoSalvoPorId(id);

        if (jogo == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Jogo não encontrado.")
                    .build();
        }
        return Response.ok(jogo).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarJogo(@PathParam("id") Long id, GameEntity novosDados) {
        GameEntity jogoAtualizado = gameService.atualizarJogo(id, novosDados);

        if (jogoAtualizado == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Jogo não encontrado para atualização.")
                    .build();
        }
        return Response.ok(jogoAtualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarJogo(@PathParam("id") Long id) {
        boolean deletado = gameService.deletarJogo(id);

        if (!deletado) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Jogo não encontrado para exclusão.")
                    .build();
        }
        return Response.noContent().build();
    }
}