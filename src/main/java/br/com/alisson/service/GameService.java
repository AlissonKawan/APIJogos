package br.com.alisson.service;

import jakarta.enterprise.context.ApplicationScoped;

//cria uma instância dessa classe e deixa disponível para injeção
@ApplicationScoped
public class GameService {

    public String buscarJogos(String nome) {
        // regra de negócio vai ficar aqui
        return "Buscando jogos com nome: " + nome;
    }
}