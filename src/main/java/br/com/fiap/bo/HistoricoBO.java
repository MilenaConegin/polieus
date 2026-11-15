package br.com.fiap.bo;

import br.com.fiap.dao.HistoricoDAO;
import br.com.fiap.to.HistoricoTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class HistoricoBO {

    HistoricoDAO historicoDAO;

    public HistoricoTO save(HistoricoTO historico) {
        historicoDAO = new HistoricoDAO();

        if (historico.getData().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "A data não pode ser futura."
            );
        }

        if (historico.getStatus() == null ||
                !historico.getStatus().matches(
                        "Pendente|Em análise|Em andamento|Concluído"
                )) {
            throw new IllegalArgumentException("Status inválido.");
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
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        return historicoDAO.findById(idHistorico);
    }

    public boolean delete(Integer idHistorico) {
        historicoDAO = new HistoricoDAO();

        if (idHistorico == null || idHistorico <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        return historicoDAO.delete(idHistorico);
    }

    public HistoricoTO update(HistoricoTO historico) {
        historicoDAO = new HistoricoDAO();


        if (historico.getData().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data não pode ser futura.");
        }

        if (historico.getStatus() == null || !historico.getStatus().matches(
                        "Pendente|Em análise|Em andamento|Concluído")) {
            throw new IllegalArgumentException("Status inválido.");
        }

        return historicoDAO.update(historico);

    }
}




