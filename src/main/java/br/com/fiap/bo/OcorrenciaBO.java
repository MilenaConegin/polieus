package br.com.fiap.bo;

import br.com.fiap.dao.OcorrenciaDAO;
import br.com.fiap.to.OcorrenciaTO;

import java.util.ArrayList;

public class OcorrenciaBO {

    OcorrenciaDAO ocorrenciaDAO;

    public OcorrenciaTO save(OcorrenciaTO ocorrencia){
        ocorrenciaDAO = new OcorrenciaDAO();
        return ocorrenciaDAO.save(ocorrencia);
    }

    public ArrayList<OcorrenciaTO> findAll(){
        ocorrenciaDAO = new OcorrenciaDAO();
        return ocorrenciaDAO.findAll();
    }

    public OcorrenciaTO findById(Integer idOcorrencia){
        ocorrenciaDAO = new OcorrenciaDAO();

        if (idOcorrencia == null || idOcorrencia <= 0) {
            throw new IllegalArgumentException(
                    "Informe um ID válido."
            );
        }

        return ocorrenciaDAO.findById(idOcorrencia);
    }

    public boolean delete(Integer idOcorrencia){
        ocorrenciaDAO = new OcorrenciaDAO();

        if (idOcorrencia == null || idOcorrencia <= 0) {
            throw new IllegalArgumentException(
                    "Informe um ID válido."
            );
        }

        return ocorrenciaDAO.delete(idOcorrencia);
    }

    public OcorrenciaTO update(OcorrenciaTO ocorrencia){
        ocorrenciaDAO = new OcorrenciaDAO();
        return ocorrenciaDAO.update(ocorrencia);
    }
}
