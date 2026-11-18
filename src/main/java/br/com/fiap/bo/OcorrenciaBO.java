
package br.com.fiap.bo;

import br.com.fiap.dao.OcorrenciaDAO;
import br.com.fiap.to.OcorrenciaTO;

import java.util.ArrayList;

public class OcorrenciaBO {

    private OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();

    public OcorrenciaTO save(OcorrenciaTO ocorrencia) {
        ocorrenciaDAO = new OcorrenciaDAO();

        if (ocorrencia == null) {
            throw new RuntimeException("Ocorrência não informada!");
        }

        if (ocorrencia.getIdUsuario() == null || ocorrencia.getIdUsuario() <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido!");
        }

        if (ocorrencia.getCategoria() == null || ocorrencia.getCategoria().isBlank()) {
            throw new RuntimeException("Categoria não informada!");
        }

        if (ocorrencia.getDescricao() == null || ocorrencia.getDescricao().isBlank()) {
            throw new RuntimeException("Descrição não informada!");
        }

        if (ocorrencia.getEndereco() == null || ocorrencia.getEndereco().isBlank()) {
            throw new RuntimeException("Endereço não informado!");
        }

        if (ocorrencia.getDepartamento() == null || ocorrencia.getDepartamento().isBlank()) {
            throw new RuntimeException("Departamento não informado!");
        }

        if (ocorrencia.getCep() == null || ocorrencia.getCep().isBlank()) {
            throw new RuntimeException("CEP não informado!");
        }

        return ocorrenciaDAO.save(ocorrencia);
    }

    public ArrayList<OcorrenciaTO> findAll() {
        ocorrenciaDAO = new OcorrenciaDAO();
        return ocorrenciaDAO.findAll();
    }

    public OcorrenciaTO findById(Integer idOcorrencia) {
        ocorrenciaDAO = new OcorrenciaDAO();

        if (idOcorrencia == null || idOcorrencia <= 0) {
            throw new IllegalArgumentException("ID da ocorrência inválido!");
        }

        return ocorrenciaDAO.findById(idOcorrencia);
    }

    public ArrayList<OcorrenciaTO> findByUsuario(Integer idUsuario) {
        ocorrenciaDAO = new OcorrenciaDAO();

        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido!");
        }

        return ocorrenciaDAO.findByUsuario(idUsuario);
    }

    public boolean delete(Integer idOcorrencia) {
        ocorrenciaDAO = new OcorrenciaDAO();

        if (idOcorrencia == null || idOcorrencia <= 0) {
            throw new IllegalArgumentException("ID da ocorrência inválido!");
        }

        return ocorrenciaDAO.delete(idOcorrencia);
    }

    public OcorrenciaTO update(OcorrenciaTO ocorrencia) {
        ocorrenciaDAO = new OcorrenciaDAO();

        if (ocorrencia == null) {
            throw new RuntimeException("Ocorrência não informada!");
        }

        if (ocorrencia.getIdOcorrencia() == null || ocorrencia.getIdOcorrencia() <= 0) {
            throw new IllegalArgumentException("ID da ocorrência inválido!");
        }

        if (ocorrencia.getIdUsuario() == null || ocorrencia.getIdUsuario() <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido!");
        }

        if (ocorrencia.getCategoria() == null || ocorrencia.getCategoria().isBlank()) {
            throw new RuntimeException("Categoria não informada!");
        }

        if (ocorrencia.getDescricao() == null || ocorrencia.getDescricao().isBlank()) {
            throw new RuntimeException("Descrição não informada!");
        }

        if (ocorrencia.getEndereco() == null || ocorrencia.getEndereco().isBlank()) {
            throw new RuntimeException("Endereço não informado!");
        }

        if (ocorrencia.getDepartamento() == null || ocorrencia.getDepartamento().isBlank()) {
            throw new RuntimeException("Departamento não informado!");
        }

        if (ocorrencia.getCep() == null || ocorrencia.getCep().isBlank()) {
            throw new RuntimeException("CEP não informado!");
        }

        return ocorrenciaDAO.update(ocorrencia);
    }
}
