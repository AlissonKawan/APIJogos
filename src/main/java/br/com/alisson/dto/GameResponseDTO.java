package br.com.alisson.dto;

/*
    Essa classe formata os dados que vêm da API externa RAWG
    para a resposta da nossa API.

    O Service faz a conversão de RawGameDTO para GameResponseDTO.

    Aqui usamos rawgId para deixar claro que esse ID veio da API RAWG,
    e não é o ID interno do nosso banco de dados.
 */
public class GameResponseDTO {

    private Long rawgId;
    private String nome;
    private Double nota;
    private String dataLancamento;
    private String imagem;

    public GameResponseDTO(Long rawgId, String nome, Double nota, String dataLancamento, String imagem) {
        this.rawgId = rawgId;
        this.nome = nome;
        this.nota = nota;
        this.dataLancamento = dataLancamento;
        this.imagem = imagem;
    }

    public Long getRawgId() {
        return rawgId;
    }

    public String getNome() {
        return nome;
    }

    public Double getNota() {
        return nota;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public String getImagem() {
        return imagem;
    }
}