package br.com.fiap.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class HistoricoTO {
    private Integer idHistorico;

    @NotBlank
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

    public HistoricoTO(){

    }

    public HistoricoTO(Integer idHistorico, String protocolo, String categoria, LocalDate data, String status) {
        setIdHistorico(idHistorico);
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
