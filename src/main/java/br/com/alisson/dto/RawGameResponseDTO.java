package br.com.alisson.dto;

import java.util.List;

public class RawGameResponseDTO {

    private List<RawGameDTO> results;

    public List<RawGameDTO> getResults() {
        return results;
    }

    public void setResults(List<RawGameDTO> results) {
        this.results = results;
    }
}