package br.com.alisson.service;

import br.com.alisson.client.RawgClient;
import br.com.alisson.dto.RawGameResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class GameService {

    @Inject
    @RestClient
    RawgClient rawgClient;

    @ConfigProperty(name = "rawg.api.key")
    String apiKey;

    public RawGameResponseDTO buscarJogos(String nome) {

        return rawgClient.buscarJogosPorNome(nome, apiKey);
    }
}