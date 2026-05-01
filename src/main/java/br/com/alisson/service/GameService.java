package br.com.alisson.service;

import br.com.alisson.client.RawgClient;
import br.com.alisson.dto.RawGameResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/*
    O Service é a lógica, o cérebro do negócio.
    Ele busca dados, valida informações e executa tarefas.
    O Service também é importante para colocar regras de negócio.
 */

// Serve para o Quarkus gerenciar essa classe.
// Aqui basicamente faz com que essa classe possa ser chamada com @Inject em outro lugar.
@ApplicationScoped
public class GameService {

    // Serve para pedir uma dependência pronta para o Quarkus.
    @Inject

    // Serve para avisar que o objeto injetado é um client HTTP externo.
    // Quarkus, injeta aqui o client HTTP da RAWG.
    @RestClient
    RawgClient rawgClient;

    // Isso pega o valor do application.properties.
    @ConfigProperty(name = "rawg.api.key")
    String apiKey;

    public RawGameResponseDTO buscarJogos(String nome) {
        return rawgClient.buscarJogosPorNome(nome, apiKey);
    }
}