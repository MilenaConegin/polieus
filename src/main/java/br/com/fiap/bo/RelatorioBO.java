
package br.com.fiap.bo;

import br.com.fiap.dao.RelatorioDAO;
import br.com.fiap.exception.RecursoNaoEncontradoException;
import br.com.fiap.model.Relatorio;
import br.com.fiap.to.RelatorioTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RelatorioBO {

    private final RelatorioDAO relatorioDAO;
    private final ModelMapper modelMapper;

    public RelatorioBO(RelatorioDAO relatorioDAO, ModelMapper modelMapper) {
        this.relatorioDAO = relatorioDAO;
        this.modelMapper = modelMapper;
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

        Relatorio model = modelMapper.map(relatorio, Relatorio.class);

        Relatorio resultado = relatorioDAO.save(model);

        return modelMapper.map(resultado, RelatorioTO.class);
    }

    public ArrayList<RelatorioTO> findAll(){
        ArrayList<Relatorio> listaModel = relatorioDAO.findAll();
        ArrayList<RelatorioTO> listaTO = new ArrayList<>();

        for (Relatorio relatorio : listaModel) {
            listaTO.add(modelMapper.map(relatorio, RelatorioTO.class));
        }

        return listaTO;
    }

    public RelatorioTO findById(Integer idRelatorio){

        if (idRelatorio == null || idRelatorio <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        Relatorio relatorio = relatorioDAO.findById(idRelatorio);

        if (relatorio == null) {
            throw new RecursoNaoEncontradoException("Relatório não encontrado!");
        }

        return modelMapper.map(relatorio, RelatorioTO.class);
    }

    public boolean delete(Integer idRelatorio){

        if (idRelatorio == null || idRelatorio <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        boolean excluido = relatorioDAO.delete(idRelatorio);

        if (!excluido) {
            throw new RecursoNaoEncontradoException("Relatório não encontrado!");
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

        Relatorio model = modelMapper.map(relatorio, Relatorio.class);

        Relatorio resultado = relatorioDAO.update(model);

        if (resultado == null) {
            throw new RecursoNaoEncontradoException("Relatório não encontrado!");
        }

        return modelMapper.map(resultado, RelatorioTO.class);
    }
}
