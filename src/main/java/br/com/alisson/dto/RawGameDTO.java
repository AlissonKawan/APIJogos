package br.com.alisson.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RawGameDTO {

    private Long id;
    private String name;
    private Double rating;
    private String released;

    //como o backgound image vem diferente com underline preciso colocar essa anotação para seguir o padrão da API
    @JsonProperty("background_image")
    private String backgroundImage;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getRating() {
        return rating;
    }

    public String getReleased() {
        return released;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }
}