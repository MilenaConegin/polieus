
package br.com.fiap.bo;

import br.com.fiap.dao.RelatorioDAO;
import br.com.fiap.to.RelatorioTO;

import java.util.ArrayList;

public class RelatorioBO {

    RelatorioDAO relatorioDAO;

    public RelatorioTO save(RelatorioTO relatorio){
       relatorioDAO = new RelatorioDAO();

        if (relatorio.getCategoria() == null ||
                relatorio.getCategoria().isBlank()){
            throw new IllegalArgumentException("Informe a categoria do relatório.");
        }

        if (relatorio.getPrioridade() == null ||
                (!relatorio.getPrioridade().equals("Alta") &&
                        !relatorio.getPrioridade().equals("Média") &&
                        !relatorio.getPrioridade().equals("Baixa"))){
            throw new IllegalArgumentException("Informe uma prioridade válida (Alta, Média ou Baixa).");
        }

        return relatorioDAO.save(relatorio);
    }

    public ArrayList<RelatorioTO> findAll(){
        relatorioDAO = new RelatorioDAO();
        return relatorioDAO.findAll();
    }

    public RelatorioTO findById(Integer idRelatorio){
        relatorioDAO = new RelatorioDAO();

        if (idRelatorio == null || idRelatorio <= 0){
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        return relatorioDAO.findById(idRelatorio);
    }

    public boolean delete(Integer idRelatorio){
        relatorioDAO = new RelatorioDAO();

        if (idRelatorio == null || idRelatorio <= 0){
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        return relatorioDAO.delete(idRelatorio);
    }

    public RelatorioTO update(RelatorioTO relatorio){
        relatorioDAO = new RelatorioDAO();

        if (relatorio.getIdRelatorio() == null ||
                relatorio.getIdRelatorio() <= 0){
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        if (relatorio.getCategoria() == null ||
                relatorio.getCategoria().isBlank()){
            throw new IllegalArgumentException("Informe a categoria do relatório.");
        }

        if (relatorio.getPrioridade() == null ||
                (!relatorio.getPrioridade().equals("Alta") &&
                        !relatorio.getPrioridade().equals("Média") &&
                        !relatorio.getPrioridade().equals("Baixa"))){
            throw new IllegalArgumentException("Informe uma prioridade válida.");
        }

        return relatorioDAO.update(relatorio);
    }
}
