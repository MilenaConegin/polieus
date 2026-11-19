
package br.com.fiap.model;

public class Relatorio {

    private Integer idRelatorio;
    private String categoria;
    private String prioridade;

    public Relatorio() {

    }

    public Relatorio(Integer idRelatorio, String categoria, String prioridade) {
        setPrioridade(prioridade);
        setCategoria(categoria);
        setIdRelatorio(idRelatorio);
    }

    public Integer getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(Integer idRelatorio) {
        this.idRelatorio = idRelatorio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
}
