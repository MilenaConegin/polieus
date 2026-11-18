
package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class OcorrenciaTO {

    private Integer idOcorrencia;

    @NotNull
    private Integer idUsuario;

    @NotBlank
    private String categoria;

    @NotBlank
    private String descricao;

    @NotBlank
    private String endereco;

    @NotBlank
    private String departamento;

    @NotBlank
    private String cep;

    public OcorrenciaTO() {

    }

    public OcorrenciaTO(Integer idOcorrencia, Integer idUsuario, String categoria, String descricao, String endereco, String departamento, String cep) {
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
