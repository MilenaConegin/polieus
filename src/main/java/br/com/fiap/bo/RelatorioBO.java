
package br.com.fiap.bo;

import br.com.fiap.dao.RelatorioDAO;
import br.com.fiap.exception.RecursoNaoEncontradoException;
import br.com.fiap.to.RelatorioTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RelatorioBO {

    private final RelatorioDAO relatorioDAO;

    public RelatorioBO(RelatorioDAO relatorioDAO) {
        this.relatorioDAO = relatorioDAO;
    }

    public RelatorioTO save(RelatorioTO relatorio){

        if (relatorio == null) {
            throw new IllegalArgumentException("Relatório não informado!");
        }

        if (relatorio.getCategoria() == null ||
                relatorio.getCategoria().isBlank()) {
            throw new IllegalArgumentException("Informe uma categoria!");
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
        return relatorioDAO.findAll();
    }

    public RelatorioTO findById(Integer idRelatorio){

        if (idRelatorio == null || idRelatorio <= 0){
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        RelatorioTO relatorio = relatorioDAO.findById(idRelatorio);
        if (relatorio == null) {
            throw new RecursoNaoEncontradoException("Relatório não encontrado!");
        }

        return relatorio;
    }

    public boolean delete(Integer idRelatorio){

        if (idRelatorio == null || idRelatorio <= 0){
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        boolean excluido = relatorioDAO.delete(idRelatorio);

        if (!excluido) {
            throw new RecursoNaoEncontradoException(
                    "Relatório não encontrado!"
            );
        }

        return true;
    }

    public RelatorioTO update(RelatorioTO relatorio) {

        if (relatorio == null) {
            throw new IllegalArgumentException("Relatório não informado!");
        }

        if (relatorio.getIdRelatorio() == null ||
                relatorio.getIdRelatorio() <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        if (relatorio.getCategoria() == null ||
                relatorio.getCategoria().isBlank()) {
            throw new IllegalArgumentException("Informe uma categoria!");
        }

        if (relatorio.getPrioridade() == null ||
                (!relatorio.getPrioridade().equals("Alta") &&
                        !relatorio.getPrioridade().equals("Média") &&
                        !relatorio.getPrioridade().equals("Baixa"))) {
            throw new IllegalArgumentException("Informe uma prioridade válida.");
        }

        RelatorioTO resultado = relatorioDAO.update(relatorio);

        if (resultado == null) {
            throw new RecursoNaoEncontradoException(
                    "Relatório não encontrado!"
            );
        }
        return resultado;
    }
}
