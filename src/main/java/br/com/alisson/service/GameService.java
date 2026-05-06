package br.com.alisson.service;

import br.com.alisson.client.RawgClient;
import br.com.alisson.dto.GameResponseDTO;
import br.com.alisson.dto.RawGameDTO;
import br.com.alisson.dto.RawGameResponseDTO;
import br.com.alisson.entity.GameEntity;
import br.com.alisson.repository.GameRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

    @Inject
    GameRepository gameRepository;

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

    /*
        Recebe um objeto GameEntity salva no banco
        usando o repository e depois devolve o objeto salvo
        Transactional diz: tudo que acontecer nesse metodo deve acontecer numa transação de banco
        por traz desse metodo o quarkus faz:
        -abre transação
        -executa persist
        -se não der erro, confirma
        -se der erro, desfaz
        -fecha transação
     */
    @Transactional
    public GameEntity salvarJogo(GameEntity game) {
        gameRepository.persist(game);
        return game;
    }
    public List<GameEntity> listarJogosSalvos() {
        return gameRepository.listAll();
    }

    public GameEntity buscarJogoSalvoPorId(Long id) {
        return gameRepository.findById(id);
    }

    @Transactional
    public boolean deletarJogo(Long id) {
        return gameRepository.deleteById(id);
    }

    /*
        aqui vamos receber um put do resource. e quando recebermos vamos colocar como parametro "gameAtualizado"
        ai o quarkus vai validar usando o Transactional
     */
    @Transactional
    public GameEntity atualizarJogo(Long id, GameEntity gameAtualizado) {

        GameEntity gameExistente = gameRepository.findById(id);

        if (gameExistente == null) {
            return null;
        }

        gameExistente.setNome(gameAtualizado.getNome());
        gameExistente.setNota(gameAtualizado.getNota());
        gameExistente.setDataLancamento(gameAtualizado.getDataLancamento());
        gameExistente.setImagem(gameAtualizado.getImagem());

        return gameExistente;
    }

}