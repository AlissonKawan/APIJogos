package br.com.alisson.entity;

import jakarta.persistence.*;

// aqui estou falando para o Quarkus que essa classe é uma entity,
// ou seja, essa classe vai representar uma tabela no banco de dados.
// o nome da tabela será games.
// JPA/Hibernate é quem controlará essa classe.
@Entity
@Table(name = "games")
public class GameEntity {

    /*
        Esse é o ID interno do meu banco de dados.

        @Id define que esse campo é a chave primária da tabela.

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        significa que o próprio banco vai gerar o valor do ID automaticamente.

        Exemplo:
        Primeiro jogo salvo -> id = 1
        Segundo jogo salvo  -> id = 2
        Terceiro jogo salvo -> id = 3
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
        Esse campo guarda o ID original do jogo vindo da API RAWG.

        Ele é diferente do id acima.

        id     -> ID gerado pelo meu banco.
        rawgId -> ID que veio da API externa RAWG.

        Exemplo:
        id do meu banco: 1
        rawgId da RAWG: 3498
     */
    @Column(name = "rawg_id")
    private Long rawgId;

    /*
        Nome do jogo que será salvo no banco.
     */
    private String nome;

    /*
        Nota/rating do jogo.
     */
    private Double nota;

    /*
        Aqui estou dizendo que no Java o atributo se chama dataLancamento,
        mas no banco a coluna será chamada data_lancamento.

        Isso deixa o Java com padrão camelCase
        e o banco com padrão snake_case.
     */
    @Column(name = "data_lancamento")
    private String dataLancamento;

    /*
        URL da imagem do jogo.

        Coloquei length = 1000 porque links de imagem podem ser grandes.
        Se deixar o padrão, alguns bancos podem limitar em 255 caracteres.
     */
    @Column(length = 1000)
    private String imagem;

    public Long getId() {
        return id;
    }

    public Long getRawgId() {
        return rawgId;
    }

    public void setRawgId(Long rawgId) {
        this.rawgId = rawgId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}