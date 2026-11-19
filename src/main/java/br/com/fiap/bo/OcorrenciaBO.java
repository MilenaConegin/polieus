
package br.com.fiap.bo;

import br.com.fiap.dao.OcorrenciaDAO;
import br.com.fiap.exception.RecursoNaoEncontradoException;
import br.com.fiap.to.OcorrenciaTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OcorrenciaBO {


    private final OcorrenciaDAO ocorrenciaDAO;

    public OcorrenciaBO(OcorrenciaDAO ocorrenciaDAO) {
        this.ocorrenciaDAO = ocorrenciaDAO;
    }

    public OcorrenciaTO save(OcorrenciaTO ocorrencia) {


        if (ocorrencia == null) {
            throw new IllegalArgumentException("Ocorrência não informada!");
        }

        if (ocorrencia.getIdUsuario() == null || ocorrencia.getIdUsuario() <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido!");
        }

        if (ocorrencia.getCategoria() == null || ocorrencia.getCategoria().isBlank()) {
            throw new IllegalArgumentException("Categoria não informada!");
        }

        if (ocorrencia.getDescricao() == null || ocorrencia.getDescricao().isBlank()) {
            throw new IllegalArgumentException("Descrição não informada!");
        }

        if (ocorrencia.getEndereco() == null || ocorrencia.getEndereco().isBlank()) {
            throw new IllegalArgumentException("Endereço não informado!");
        }

        if (ocorrencia.getDepartamento() == null || ocorrencia.getDepartamento().isBlank()) {
            throw new IllegalArgumentException("Departamento não informado!");
        }

        if (ocorrencia.getCep() == null || ocorrencia.getCep().isBlank()) {
            throw new IllegalArgumentException("CEP não informado!");
        }

        return ocorrenciaDAO.save(ocorrencia);
    }

    public ArrayList<OcorrenciaTO> findAll() {

        return ocorrenciaDAO.findAll();
    }

    public OcorrenciaTO findById(Integer idOcorrencia) {

        if (idOcorrencia == null || idOcorrencia <= 0) {
            throw new IllegalArgumentException("ID da ocorrência inválido!");
        }

        OcorrenciaTO ocorrencia = ocorrenciaDAO.findById(idOcorrencia);

        if (ocorrencia == null) {
            throw new RecursoNaoEncontradoException("Ocorrência não encontrada!");
        }

        return ocorrenciaDAO.findById(idOcorrencia);
    }

    public ArrayList<OcorrenciaTO> findByUsuario(Integer idUsuario) {

        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido!");
        }

        return ocorrenciaDAO.findByUsuario(idUsuario);
    }

    public boolean delete(Integer idOcorrencia) {

        if (idOcorrencia == null || idOcorrencia <= 0) {
            throw new IllegalArgumentException("ID da ocorrência inválido!");
        }

        if (!ocorrenciaDAO.delete(idOcorrencia)) {
            throw new RecursoNaoEncontradoException("Ocorrência não encontrada!");
        }

        return ocorrenciaDAO.delete(idOcorrencia);
    }

    public OcorrenciaTO update(OcorrenciaTO ocorrencia) {

        if (ocorrencia == null) {
            throw new IllegalArgumentException("Ocorrência não informada!");
        }

        if (ocorrencia.getIdOcorrencia() == null || ocorrencia.getIdOcorrencia() <= 0) {
            throw new IllegalArgumentException("ID da ocorrência inválido!");
        }

        if (ocorrencia.getIdUsuario() == null || ocorrencia.getIdUsuario() <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido!");
        }

        if (ocorrencia.getCategoria() == null || ocorrencia.getCategoria().isBlank()) {
            throw new IllegalArgumentException("Categoria não informada!");
        }

        if (ocorrencia.getDescricao() == null || ocorrencia.getDescricao().isBlank()) {
            throw new IllegalArgumentException("Descrição não informada!");
        }

        if (ocorrencia.getEndereco() == null || ocorrencia.getEndereco().isBlank()) {
            throw new IllegalArgumentException("Endereço não informado!");
        }

        if (ocorrencia.getDepartamento() == null || ocorrencia.getDepartamento().isBlank()) {
            throw new IllegalArgumentException("Departamento não informado!");
        }

        if (ocorrencia.getCep() == null || ocorrencia.getCep().isBlank()) {
            throw new IllegalArgumentException("CEP não informado!");
        }

        return ocorrenciaDAO.update(ocorrencia);
    }
}
