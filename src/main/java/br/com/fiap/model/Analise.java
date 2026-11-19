
package br.com.fiap.model;

import java.time.LocalDate;

public class Analise {

    private Integer idAnalise;
    private String protocolo;
    private String categoria;
    private String localizacao;
    private String prioridade;
    private String status;
    private LocalDate data;

    public Analise() {

    }

    public Analise(Integer idAnalise, String protocolo, String categoria,
                   String localizacao, String prioridade, String status, LocalDate data) {
        setIdAnalise(idAnalise);
        setProtocolo(protocolo);
        setCategoria(categoria);
        setLocalizacao(localizacao);
        setPrioridade(prioridade);
        setStatus(status);
        setData(data);
    }

    public Integer getIdAnalise() {
        return idAnalise;
    }

    public void setIdAnalise(Integer idAnalise) {
        this.idAnalise = idAnalise;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
