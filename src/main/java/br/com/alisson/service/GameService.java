package br.com.alisson.service;

import br.com.alisson.client.RawgClient;
import br.com.alisson.dto.GameResponseDTO;
import br.com.alisson.dto.RawGameDTO;
import br.com.alisson.dto.RawGameResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

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
    //basicamente como eu defini na classe client para registrar ele como rest client eu posso injetar ele aqui também.
    @RestClient
    RawgClient rawgClient;

    // Isso pega o valor do application.properties.
    @ConfigProperty(name = "rawg.api.key")
    String apiKey;

    public List<GameResponseDTO> buscarJogos(String nome) {

        /*
        aqui estou criando uma variavel para facilitar minha busca de jogos por nome
        ou seja respostaRawg vai guardar essa operação de chmar client e o metodo dele que é buscar jogos por nome!
        exemplo: busque um jogo na Api rawg, quando a resposta voltar guarde ela nessa variavel
        so que respostaRawg é a resposta completa da API
         */
        RawGameResponseDTO respostaRawg = rawgClient.buscarJogosPorNome(nome, apiKey);

        /*
        aqui estou criando uma lista do tipo RawGameDTO que é a representação de cada jogo
        e estou definindo que jogosRawg vai receber a resposta da api
        quero somente o results da api por isso estou usando o getResults

        "Me dá a lista de jogos que veio dentro de results"
         */
        List<RawGameDTO> jogosRawg = respostaRawg.getResults();

        /*
            “Para cada jogo cru da lista, crie um jogo tratado e
            depois junte tudo em uma nova lista”
            O stream() transforma essa lista em uma sequência de processamento.
            O map serve para transformar uma coisa em outra.

            o jogo é uma variavel temporaria
            usamos o lambda para transformar ele em um objeto GameResponse

         */
        List<GameResponseDTO> jogosTratados = jogosRawg.stream()
                //ou seja para cada RawGame, ele cria um ResponseDTO
                .map(jogo -> new GameResponseDTO(
                        jogo.getId(),
                        jogo.getName(),
                        jogo.getRating(),
                        jogo.getReleased(),
                        jogo.getBackgroundImage()
                ))
                .toList();
        //depois que o maps transforma a lista, ele passa para o toList para criar uma nova lista final

        return jogosTratados;
    } //todo esse método foi para transformar uma resposta formatada do tipo GameResponseDTO
}