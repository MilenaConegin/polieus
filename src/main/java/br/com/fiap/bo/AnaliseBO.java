
package br.com.fiap.bo;

import br.com.fiap.dao.AnaliseDAO;
import br.com.fiap.exception.RecursoNaoEncontradoException;
import br.com.fiap.model.Analise;
import br.com.fiap.to.AnaliseTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class AnaliseBO {

    private final AnaliseDAO analiseDAO;
    private final ModelMapper modelMapper;

    public AnaliseBO(AnaliseDAO analiseDAO, ModelMapper modelMapper) {
        this.analiseDAO = analiseDAO;
        this.modelMapper = modelMapper;
    }

    public AnaliseTO save(AnaliseTO analise) {

        if (analise == null || analise.getData() == null) {
            throw new IllegalArgumentException(
                    "Informe os dados e a data da análise."
            );
        }

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

        Analise model = modelMapper.map(analise, Analise.class);

        Analise resultado = analiseDAO.save(model);

        return modelMapper.map(resultado, AnaliseTO.class);
    }

    public ArrayList<AnaliseTO> findAll() {
        ArrayList<Analise> analises = analiseDAO.findAll();
        ArrayList<AnaliseTO> resultado = new ArrayList<>();

        for (Analise analise : analises) {
            resultado.add(modelMapper.map(analise, AnaliseTO.class));
        }

        return resultado;
    }

    public AnaliseTO findById(Integer idAnalise) {

        if (idAnalise == null || idAnalise <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        Analise analise = analiseDAO.findById(idAnalise);

        if (analise == null) {
            throw new RecursoNaoEncontradoException("Análise não encontrada!");
        }

        return modelMapper.map(analise, AnaliseTO.class);
    }

    public boolean delete(Integer idAnalise) {

        if (idAnalise == null || idAnalise <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        boolean excluido = analiseDAO.delete(idAnalise);

        if (!excluido) {
            throw new RecursoNaoEncontradoException(
                    "Análise não encontrada para exclusão!"
            );
        }

        return true;
    }

    public AnaliseTO update(AnaliseTO analise) {

        if (analise == null || analise.getData() == null) {
            throw new IllegalArgumentException("Informe os dados e a data da análise.");
        }

        if (analise.getIdAnalise() == null || analise.getIdAnalise() <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

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

        Analise model = modelMapper.map(analise, Analise.class);

        Analise resultado = analiseDAO.update(model);

        if (resultado == null) {
            throw new RecursoNaoEncontradoException(
                    "Análise não encontrada para atualização!"
            );
        }

        return modelMapper.map(resultado, AnaliseTO.class);
    }
}
