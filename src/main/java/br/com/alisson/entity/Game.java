package br.com.alisson.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Game {

    @Id
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "rating")
    public Double rating;

    @Column(name = "released")
    public String released;

    @Column(name = "background_image")
    public String background_image;
}
