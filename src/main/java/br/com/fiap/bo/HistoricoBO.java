
package br.com.fiap.bo;

import br.com.fiap.dao.HistoricoDAO;
import br.com.fiap.exception.RecursoNaoEncontradoException;
import br.com.fiap.to.HistoricoTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class HistoricoBO {

    private final HistoricoDAO historicoDAO;

    public HistoricoBO(HistoricoDAO historicoDAO) {
        this.historicoDAO = historicoDAO;
    }

    public HistoricoTO save(HistoricoTO historico) {

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

        HistoricoTO resultado = historicoDAO.save(historico);

        if (resultado == null) {
            throw new IllegalStateException("Não foi possível cadastrar o histórico!");
        }

        return resultado;
    }

    public ArrayList<HistoricoTO> findAll() {
        return historicoDAO.findAll();
    }

    public HistoricoTO findById(Integer idHistorico) {

        if (idHistorico == null || idHistorico <= 0) {
            throw new IllegalArgumentException("Informe um ID válido!");
        }

        HistoricoTO historico = historicoDAO.findById(idHistorico);

        if (historico == null) {
            throw new RecursoNaoEncontradoException("Histórico não encontrado!");
        }

        return historico;
    }

    public ArrayList<HistoricoTO> findByOcorrencia(Integer idOcorrencia) {

        if (idOcorrencia == null || idOcorrencia <= 0) {
            throw new IllegalArgumentException("Informe uma ocorrência válida!");
        }

        return historicoDAO.findByOcorrencia(idOcorrencia);
    }

    public boolean delete(Integer idHistorico) {

        if (idHistorico == null || idHistorico <= 0) {
            throw new IllegalArgumentException("Informe um ID válido!");
        }

        boolean resultado = historicoDAO.delete(idHistorico);

        if (!resultado) {
            throw new RecursoNaoEncontradoException("Histórico não encontrado!");
        }

        return true;
    }

    public HistoricoTO update(HistoricoTO historico) {

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

        HistoricoTO resultado = historicoDAO.update(historico);

        if (resultado == null) {
            throw new RecursoNaoEncontradoException("Histórico não encontrado!");
        }

        return resultado;
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
            throw new RecursoNaoEncontradoException(
                    "Não existe histórico para essa ocorrência!");
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
