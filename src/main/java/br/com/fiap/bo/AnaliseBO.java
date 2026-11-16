
package br.com.fiap.bo;

import br.com.fiap.dao.AnaliseDAO;
import br.com.fiap.to.AnaliseTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class AnaliseBO {

    AnaliseDAO analiseDAO;

    public AnaliseTO save(AnaliseTO analise) {
        analiseDAO = new AnaliseDAO();
        return analiseDAO.save(analise);
    }

    public ArrayList<AnaliseTO> findAll() {

        analiseDAO = new AnaliseDAO();
        return analiseDAO.findAll();
    }

    public AnaliseTO findById(Integer idAnalise) {

        analiseDAO = new AnaliseDAO();

        if (idAnalise == null || idAnalise <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        return analiseDAO.findById(idAnalise);
    }

    public boolean delete(Integer idAnalise) {

        analiseDAO = new AnaliseDAO();

        if (idAnalise == null || idAnalise <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        return analiseDAO.delete(idAnalise);
    }

    public AnaliseTO update(AnaliseTO analise) {

        analiseDAO = new AnaliseDAO();

        if (analise.getData().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data não pode ser futura.");
        }

        if (analise.getStatus() == null ||
                !analise.getStatus().matches(
                        "Pendente|Em análise|Em andamento|Concluído")) {
            throw new IllegalArgumentException("Status inválido.");
        }

        if (analise.getPrioridade() == null ||
                !analise.getPrioridade().matches("Alta|Média|Baixa")) {
            throw new IllegalArgumentException("Prioridade inválida.");
        }

        return analiseDAO.update(analise);
    }
}
