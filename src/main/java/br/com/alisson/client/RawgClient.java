package br.com.alisson.client;

import br.com.alisson.dto.RawGameResponseDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/*
    Função do client: buscar ligar para API externa, ou seja, ele que chama a API para o service decidir, e o
    Resource/Controller usar/receber
 */
@RegisterRestClient(configKey = "rawg-api")
@Path("/games")
public interface RawgClient {

    @GET
    RawGameResponseDTO buscarJogosPorNome(
            @QueryParam("search") String nome,
            @QueryParam("key") String chaveApi
    );
}