
package br.com.fiap.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class HistoricoTO {

    private Integer idHistorico;

    @NotNull
    @Positive
    private Integer idOcorrencia;

    @NotBlank
    @Size(max = 30)
    private String protocolo;

    @NotBlank
    @Size(max = 100)
    private String categoria;

    @NotNull
    @PastOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate data;

    @NotBlank
    @Pattern(
            regexp = "Pendente|Em análise|Em andamento|Concluído",
            message = "Status inválido"
    )
    private String status;

    public HistoricoTO() {
    }

    public HistoricoTO(Integer idHistorico, Integer idOcorrencia, String protocolo, String categoria, LocalDate data, String status) {
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
