
package br.com.fiap.model;

import java.time.LocalDate;

public class Historico {

    private Integer idHistorico;
    private Integer idOcorrencia;
    private String protocolo;
    private String categoria;
    private LocalDate data;
    private String status;

    public Historico() {

    }

    public Historico(Integer idHistorico, Integer idOcorrencia, String protocolo,
                     String categoria, LocalDate data, String status) {
        setIdHistorico(idHistorico);
        setIdOcorrencia(idOcorrencia);
        setProtocolo(protocolo);
        setCategoria(categoria);
        setData(data);
        setStatus(status);
    }

    public Integer getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(Integer idHistorico) {
        this.idHistorico = idHistorico;
    }

    public Integer getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
