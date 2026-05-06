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

        /*
            Aqui estou validando se o usuário realmente mandou o parâmetro name.
            Se ele chamar apenas /games/search sem ?name=algumaCoisa,
            o sistema devolve erro 400, que significa requisição inválida.
         */
        if (nome == null || nome.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("O parâmetro 'name' é obrigatório.")
                    .build();
        }

        /*
            Aqui o Resource chama o Service.
            O Resource não busca direto na API RAWG.
            Ele apenas recebe a requisição HTTP e manda o Service resolver.
         */
        List<GameResponseDTO> jogos = gameService.buscarJogos(nome);

        /*
            Response.ok(jogos) devolve status 200 OK junto com a lista de jogos.
         */
        return Response.ok(jogos).build();
    }

    // Resumindo acima: esse método responde quando alguém chamar GET /games/search?name=algumJogo

    /*
        Esse endpoint serve para salvar um jogo no banco de dados.
        @POST significa que estou criando/salvando um novo recurso.
        Exemplo de chamada:
        POST http://localhost:8080/games
        O JSON enviado no body da requisição será transformado automaticamente
        em um objeto GameEntity pelo Quarkus.
     */
    @POST
    public Response salvarJogo(GameEntity game) {
        /*
            Aqui o Resource passa o objeto recebido para o Service.
            O Service vai chamar o Repository e salvar no banco.
         */
        GameEntity jogoSalvo = gameService.salvarJogo(game);
        /*
            Status 201 CREATED significa:
            "O recurso foi criado com sucesso."
         */
        return Response.status(Response.Status.CREATED)
                .entity(jogoSalvo)
                .build();
    }
    /*
        Esse endpoint lista todos os jogos salvos no banco.
        Exemplo de chamada:
        GET http://localhost:8080/games/salvos
     */
    @GET
    @Path("/salvos")
    public Response listarJogosSalvos() {
        /*
            Aqui pedimos para o Service buscar todos os jogos salvos.
         */
        List<GameEntity> jogos = gameService.listarJogosSalvos();

        /*
            Retorna status 200 OK com a lista de jogos.
         */
        return Response.ok(jogos).build();
    }
    /*
        Esse endpoint busca um jogo salvo pelo id.

        @Path("/{id}") significa que o id vem direto pela URL.

        Exemplo:
        GET http://localhost:8080/games/salvos/1
     */
    @GET
    @Path("/salvos/{id}")
    public Response buscarJogoPorId(@PathParam("id") Long id) {
        /*
            @PathParam pega o valor que veio na URL e coloca na variável id.
            Exemplo: /games/salvos/1
            Então id = 1
         */
        GameEntity jogo = gameService.buscarJogoSalvoPorId(id);

        /*
            Se o Service não encontrar o jogo, ele retorna null.
            Nesse caso devolvemos 404 NOT FOUND.
         */
        if (jogo == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Jogo não encontrado.")
                    .build();
        }
        /*
            Se encontrou, devolvemos o jogo com status 200 OK.
         */
        return Response.ok(jogo).build();
    }
    /*
        Esse endpoint atualiza um jogo já salvo no banco.
        @PUT significa atualização.
        Exemplo:
        PUT http://localhost:8080/games/1
        O id vem pela URL.
        Os novos dados vêm no body da requisição em JSON.
     */
    @PUT
    @Path("/{id}")
    public Response atualizarJogo(@PathParam("id") Long id, GameEntity novosDados) {
        /*
            Aqui mandamos para o Service:
            - o id do jogo que queremos atualizar
            - os novos dados que vieram no corpo da requisição
         */
        GameEntity jogoAtualizado = gameService.atualizarJogo(id, novosDados);
        /*
            Se o jogo não existir no banco, devolvemos 404.
         */
        if (jogoAtualizado == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Jogo não encontrado para atualização.")
                    .build();
        }
        /*
            Se atualizou com sucesso, devolvemos o jogo atualizado.
         */
        return Response.ok(jogoAtualizado).build();
    }
    /*
        Esse endpoint deleta um jogo salvo no banco.

        @DELETE significa exclusão.

        Exemplo:
        DELETE http://localhost:8080/games/1
     */
    @DELETE
    @Path("/{id}")
    public Response deletarJogo(@PathParam("id") Long id) {
        /*
            O Service tenta deletar o jogo pelo id.
            O método retorna true se conseguiu deletar.
            Retorna false se não encontrou o jogo.
         */
        boolean deletado = gameService.deletarJogo(id);
        /*
            Se não deletou, provavelmente o id não existe.
         */
        if (!deletado) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Jogo não encontrado para exclusão.")
                    .build();
        }
        /*
            Response.noContent() devolve status 204.
            204 significa:
            "Deu certo, mas não tenho conteúdo para devolver."
         */
        return Response.noContent().build();
    }
}