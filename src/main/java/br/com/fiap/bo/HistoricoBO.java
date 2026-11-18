
package br.com.fiap.bo;

import br.com.fiap.dao.HistoricoDAO;
import br.com.fiap.to.HistoricoTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class HistoricoBO {

    private HistoricoDAO historicoDAO = new HistoricoDAO();

    public HistoricoTO save(HistoricoTO historico) {
        historicoDAO = new HistoricoDAO();

        if (historico == null) {
            throw new IllegalArgumentException("Informe os dados do histórico!");
        }

        if (historico.getIdOcorrencia() == null || historico.getIdOcorrencia() <= 0) {
            throw new IllegalArgumentException("Informe uma ocorrência válida!");
        }

        if (historico.getProtocolo() == null || historico.getProtocolo().isBlank()) {
            throw new IllegalArgumentException("Informe o protocolo!");
        }

        if (historico.getProtocolo().length() > 30) {
            throw new IllegalArgumentException("O protocolo deve ter até 30 caracteres!");
        }

        if (historico.getCategoria() == null || historico.getCategoria().isBlank()) {
            throw new IllegalArgumentException("Informe a categoria!");
        }

        if (historico.getCategoria().length() > 100) {
            throw new IllegalArgumentException("A categoria deve ter até 100 caracteres!");
        }

        if (historico.getData() == null || historico.getData().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Informe uma data válida!");
        }

        if (historico.getStatus() == null ||
                !(historico.getStatus().equals("Pendente") || historico.getStatus().equals("Em análise") ||
                        historico.getStatus().equals("Em andamento") || historico.getStatus().equals("Concluído"))) {
            throw new IllegalArgumentException("Status inválido!");
        }

        return historicoDAO.save(historico);
    }

    public ArrayList<HistoricoTO> findAll() {
        historicoDAO = new HistoricoDAO();
        return historicoDAO.findAll();
    }

    public HistoricoTO findById(Integer idHistorico) {
        historicoDAO = new HistoricoDAO();

        if (idHistorico == null || idHistorico <= 0) {
            throw new IllegalArgumentException("Informe um ID válido!");
        }

        return historicoDAO.findById(idHistorico);
    }

    public ArrayList<HistoricoTO> findByOcorrencia(Integer idOcorrencia) {
        historicoDAO = new HistoricoDAO();

        if (idOcorrencia == null || idOcorrencia <= 0) {
            throw new IllegalArgumentException("Informe uma ocorrência válida!");
        }

        return historicoDAO.findByOcorrencia(idOcorrencia);
    }

    public boolean delete(Integer idHistorico) {
        historicoDAO = new HistoricoDAO();

        if (idHistorico == null || idHistorico <= 0) {
            throw new IllegalArgumentException("Informe um ID válido!");
        }

        return historicoDAO.delete(idHistorico);
    }

    public HistoricoTO update(HistoricoTO historico) {
        historicoDAO = new HistoricoDAO();

        if (historico == null) {
            throw new IllegalArgumentException("Informe os dados do histórico!");
        }

        if (historico.getIdHistorico() == null || historico.getIdHistorico() <= 0) {
            throw new IllegalArgumentException("Informe um ID válido!");
        }

        if (historico.getIdOcorrencia() == null || historico.getIdOcorrencia() <= 0) {
            throw new IllegalArgumentException("Informe uma ocorrência válida!");
        }

        if (historico.getProtocolo() == null || historico.getProtocolo().isBlank()) {
            throw new IllegalArgumentException("Informe o protocolo!");
        }

        if (historico.getProtocolo().length() > 30) {
            throw new IllegalArgumentException("O protocolo deve ter até 30 caracteres!");
        }

        if (historico.getCategoria() == null || historico.getCategoria().isBlank()) {
            throw new IllegalArgumentException("Informe a categoria!");
        }

        if (historico.getCategoria().length() > 100) {
            throw new IllegalArgumentException("A categoria deve ter até 100 caracteres!");
        }

        if (historico.getData() == null || historico.getData().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Informe uma data válida!");
        }

        if (historico.getStatus() == null ||
                !(historico.getStatus().equals("Pendente") || historico.getStatus().equals("Em análise") ||
                        historico.getStatus().equals("Em andamento") || historico.getStatus().equals("Concluído"))) {
            throw new IllegalArgumentException("Status inválido!");
        }

        return historicoDAO.update(historico);
    }

    public HistoricoTO updateStatus(Integer idOcorrencia, String status) {

        if (idOcorrencia == null || idOcorrencia <= 0) {
            throw new IllegalArgumentException("Informe uma ocorrência válida!");
        }

        if (!"Pendente".equals(status) && !"Em análise".equals(status) &&
                !"Em andamento".equals(status) && !"Concluído".equals(status)) {
            throw new IllegalArgumentException("Status inválido!");
        }

        ArrayList<HistoricoTO> historicos = historicoDAO.findByOcorrencia(idOcorrencia);

        if (historicos.isEmpty()) {
            return null;
        }

        //getLast pega o ultimo elemento do historico que contém o status mais recente da ocorrência
        HistoricoTO ultimo = historicos.getLast();

        if (ultimo.getStatus().equals(status)) {
            throw new IllegalArgumentException("A ocorrência já possui esse status!");
        }

        HistoricoTO novo = new HistoricoTO();
        novo.setIdOcorrencia(idOcorrencia);
        novo.setProtocolo(ultimo.getProtocolo());
        novo.setCategoria(ultimo.getCategoria());
        novo.setData(LocalDate.now());
        novo.setStatus(status);

        return historicoDAO.save(novo);
    }
}
