package br.com.alisson.repository;

import br.com.alisson.entity.GameEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/*
    Classe usada para se comunicar com o banco de dados

 Serve para o Quarkus gerenciar essa classe.
 Aqui basicamente faz com que essa classe possa ser chamada com @Inject em outro lugar.
 */
@ApplicationScoped
/*
quando uso PanacheRepository eu ganho varios metodos que vão me ajudar
como:
    persist(game);      // salvar
    listAll();          // listar todos
    findById(id);       // buscar por id
    deleteById(id);     // deletar por id
 */
/*
                                          essa classe é um repositorio
                                          da entidade GameEntity
*/
public class GameRepository implements PanacheRepository<GameEntity> {

}