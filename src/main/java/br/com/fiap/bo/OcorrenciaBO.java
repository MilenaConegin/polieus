
package br.com.fiap.bo;

import br.com.fiap.dao.OcorrenciaDAO;
import br.com.fiap.exception.RecursoNaoEncontradoException;
import br.com.fiap.model.Ocorrencia;
import br.com.fiap.to.OcorrenciaTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OcorrenciaBO {


    private final OcorrenciaDAO ocorrenciaDAO;
    private final ModelMapper modelMapper;

    public OcorrenciaBO(OcorrenciaDAO ocorrenciaDAO, ModelMapper modelMapper) {
        this.ocorrenciaDAO = ocorrenciaDAO;
        this.modelMapper = modelMapper;
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

        Ocorrencia model = modelMapper.map(ocorrencia, Ocorrencia.class);

        Ocorrencia resultado = ocorrenciaDAO.save(model);

        return modelMapper.map(resultado, OcorrenciaTO.class);
    }

    public ArrayList<OcorrenciaTO> findAll() {

        ArrayList<Ocorrencia> listaModel = ocorrenciaDAO.findAll();
        ArrayList<OcorrenciaTO> listaTO = new ArrayList<>();

        for (Ocorrencia ocorrencia : listaModel) {
            listaTO.add(modelMapper.map(ocorrencia, OcorrenciaTO.class));
        }

        return listaTO;
    }

    public OcorrenciaTO findById(Integer idOcorrencia) {

        if (idOcorrencia == null || idOcorrencia <= 0) {
            throw new IllegalArgumentException("ID da ocorrência inválido!");
        }

        Ocorrencia ocorrencia = ocorrenciaDAO.findById(idOcorrencia);

        if (ocorrencia == null) {
            throw new RecursoNaoEncontradoException("Ocorrência não encontrada!");
        }

        return modelMapper.map(ocorrencia, OcorrenciaTO.class);
    }

    public ArrayList<OcorrenciaTO> findByUsuario(Integer idUsuario) {

        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido!");
        }

        ArrayList<Ocorrencia> listaModel = ocorrenciaDAO.findByUsuario(idUsuario);
        ArrayList<OcorrenciaTO> listaTO = new ArrayList<>();

        for (Ocorrencia ocorrencia : listaModel) {
            listaTO.add(modelMapper.map(ocorrencia, OcorrenciaTO.class));
        }

        return listaTO;
    }

    public boolean delete(Integer idOcorrencia) {

        if (idOcorrencia == null || idOcorrencia <= 0) {
            throw new IllegalArgumentException("ID da ocorrência inválido!");
        }

        boolean excluido = ocorrenciaDAO.delete(idOcorrencia);

        if (!excluido) {
            throw new RecursoNaoEncontradoException("Ocorrência não encontrada!");
        }

        return true;
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

        Ocorrencia model = modelMapper.map(ocorrencia, Ocorrencia.class);

        Ocorrencia resultado = ocorrenciaDAO.update(model);

        if (resultado == null) {
            throw new RecursoNaoEncontradoException("Ocorrência não encontrada!");
        }

        return modelMapper.map(resultado, OcorrenciaTO.class);
    }
}
