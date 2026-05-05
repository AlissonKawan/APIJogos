package br.com.alisson.dto;


/*
    Criando essa classe para formatar os dados que vem da api externa para minha API, minha api vai tratar os dados em português, da forma que eu quero mais bonito

    o Service vai fazer a conversão de rawGame para > GameResponse
 */
public class GameResponseDTO {

    private Long id;
    private String nome;
    private Double nota;
    private String lancamento;
    private String imagem;

    public GameResponseDTO(Long id, String nome, Double nota, String lancamento, String imagem) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
        this.lancamento = lancamento;
        this.imagem = imagem;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getNota() {
        return nota;
    }

    public String getLancamento() {
        return lancamento;
    }

    public String getImagem() {
        return imagem;
    }
}