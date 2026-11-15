package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RelatorioTO {
    private Integer idRelatorio;

    @NotBlank
    private String categoria;

    @NotBlank
    @Pattern(
            regexp = "Alta|Média|Baixa",
            message = "Erro de prioridade"
    )
    private String prioridade;

    public RelatorioTO(){

    }

    public RelatorioTO(String prioridade, String categoria, Integer idRelatorio) {
        setPrioridade(prioridade);
        setCategoria(categoria);
        setIdRelatorio(idRelatorio);
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(Integer idRelatorio) {
        this.idRelatorio = idRelatorio;
    }
}
