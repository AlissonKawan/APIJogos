// Define o pacote onde essa classe está localizada
package br.com.alisson.repository;

// Importa a entidade Game que esse repositório vai gerenciar
import br.com.alisson.entity.Game;

// Importa a interface PanacheRepository do Quarkus
// Ela já vem com vários métodos prontos: listAll(), persist(), findById(), delete(), etc.
// Ou seja, não precisamos escrever nenhuma query SQL manualmente para operações básicas
import io.quarkus.hibernate.orm.panache.PanacheRepository;

// Importa a anotação que define o escopo de vida dessa classe
import jakarta.enterprise.context.ApplicationScoped;

// @ApplicationScoped diz ao Quarkus: "crie apenas UMA instância dessa classe durante toda a vida da aplicação"
// Essa instância será compartilhada e reutilizada sempre que alguém pedir via @Inject
@ApplicationScoped

// Implementa PanacheRepository<Game>:
// - O tipo entre <> diz qual entidade esse repositório gerencia (Game)
// - Herdamos automaticamente métodos como: listAll(), persist(), findById(), deleteById(), count(), etc.
public class GameRepository implements PanacheRepository<Game> {
    // Não precisamos escrever nada aqui para as operações básicas
    // O Panache já fornece tudo automaticamente via herança
}