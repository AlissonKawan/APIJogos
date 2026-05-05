package br.com.alisson.repository;

import br.com.alisson.entity.Game;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GameRepository implements PanacheRepository<Game> {
}
