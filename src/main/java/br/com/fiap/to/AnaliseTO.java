package br.com.fiap.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class AnaliseTO {
    private Integer IdAnalise;

    @NotBlank
    private String protocolo;

    @NotBlank
    @Size(max = 100)
    private String categoria;

    @NotBlank
    private String localizacao;

    @NotBlank
    @Pattern(
            regexp = "Alta|Média|Baixa",
            message = "Erro de prioridade"
    )
    private String prioridade;

    @NotBlank
    @Pattern(
            regexp = "Pendente|Em análise|Em andamento|Concluído",
            message = "Status inválido"
    )
    private String status;

    @NotNull
    @PastOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate data;

    public AnaliseTO(){

    }

    public AnaliseTO(Integer idAnalise, String protocolo, String categoria, String localizacao, String prioridade, String status, LocalDate data) {
        setIdAnalise(idAnalise);
        setProtocolo(protocolo);
        setCategoria(categoria);
        setLocalizacao(localizacao);
        setPrioridade(prioridade);
        setStatus(status);
        setData(data);
    }

    public Integer getIdAnalise() {
        return IdAnalise;
    }

    public void setIdAnalise(Integer idAnalise) {
        IdAnalise = idAnalise;
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
