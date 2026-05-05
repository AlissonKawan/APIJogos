// Define o pacote onde essa classe está localizada
package br.com.alisson.entity;

// Importa a anotação @Entity do JPA, que diz ao Hibernate que essa classe representa uma tabela no banco
import jakarta.persistence.Entity;

// Importa a anotação @Id, que marca qual campo é a chave primária da tabela
import jakarta.persistence.Id;

// Importa a anotação @Column, que mapeia um campo da classe para uma coluna específica da tabela
import jakarta.persistence.Column;

// @Entity diz ao Hibernate: "crie uma tabela no banco baseada nessa classe"
// O nome da tabela será "Game" por padrão (mesmo nome da classe)
@Entity
public class Game {

    // @Id marca esse campo como chave primária da tabela
    // Ou seja, cada jogo terá um id único que identifica ele no banco
    @Id
    public Long id;

    // @Column mapeia esse campo para a coluna "name" na tabela do banco
    // O Hibernate vai ler e gravar o nome do jogo nessa coluna
    @Column(name = "name")
    public String name;

    // Mapeia para a coluna "rating" — armazena a nota do jogo (ex: 4.8)
    @Column(name = "rating")
    public Double rating;

    // Mapeia para a coluna "released" — armazena a data de lançamento como texto (ex: "2022-02-25")
    @Column(name = "released")
    public String released;

    // Mapeia para a coluna "background_image" — armazena a URL da imagem de capa do jogo
    @Column(name = "background_image")
    public String background_image;
}