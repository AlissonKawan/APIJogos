package br.com.alisson.entity;

import jakarta.persistence.*;

//aqui estou falando para o quarkus que essa classe é uma entity, a classe q vai representar a tabela
// e o nome da tabela sera games
// JPA/Hibernate é quem controlara essa classe
@Entity
@Table(name = "games")
public class GameEntity {

    //basicamente estou definindo q id é um id para o banco e falando para ele gerar um valor
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rawgId;

    private String nome;
    private Double nota;
    private String dataLancamento;
    private String imagem;

    public Long getId() {
        return rawgId;
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
