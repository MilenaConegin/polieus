
package br.com.fiap.model;

public class Ocorrencia {

    private Integer idOcorrencia;
    private Integer idUsuario;
    private String categoria;
    private String descricao;
    private String endereco;
    private String departamento;
    private String cep;

    public Ocorrencia() {

    }

    public Ocorrencia(Integer idOcorrencia, Integer idUsuario, String categoria,
                      String descricao, String endereco, String departamento, String cep) {
        setIdOcorrencia(idOcorrencia);
        setIdUsuario(idUsuario);
        setCategoria(categoria);
        setDescricao(descricao);
        setEndereco(endereco);
        setDepartamento(departamento);
        setCep(cep);
    }

    public Integer getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
