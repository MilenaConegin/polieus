
package br.com.fiap.bo;

import br.com.fiap.dao.AdministrativoDAO;
import br.com.fiap.to.AdministrativoTO;

import java.util.ArrayList;

public class AdministrativoBO {

    AdministrativoDAO administrativoDAO;

    public AdministrativoTO save(AdministrativoTO administrativo) {
        administrativoDAO = new AdministrativoDAO();

        if (administrativo.getEmail() == null || !administrativo.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Informe um e-mail válido.");
        }

        if (administrativo.getSenha() == null || !administrativo.getSenha().matches(
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_]).{8,100}$")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos 8 caracteres, um número, uma letra maiúscula, uma letra minúscula e um caractere especial.");
        }

        return administrativoDAO.save(administrativo);
    }

    public ArrayList<AdministrativoTO> findAll() {
        administrativoDAO = new AdministrativoDAO();
        return administrativoDAO.findAll();
    }

    public AdministrativoTO findById(Integer idAdmin) {
        administrativoDAO = new AdministrativoDAO();

        if (idAdmin == null || idAdmin <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        return administrativoDAO.findById(idAdmin);
    }

    public boolean delete(Integer idAdmin) {
        administrativoDAO = new AdministrativoDAO();

        if (idAdmin == null || idAdmin <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        return administrativoDAO.delete(idAdmin);
    }

    public AdministrativoTO update(AdministrativoTO administrativo) {
        administrativoDAO = new AdministrativoDAO();

        if (administrativo.getEmail() == null || !administrativo.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Informe um e-mail válido.");
        }

        if (administrativo.getSenha() == null || !administrativo.getSenha().matches(
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_]).{8,100}$")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos 8 caracteres, um número, uma letra maiúscula, uma letra minúscula e um caractere especial.");
        }

        return administrativoDAO.update(administrativo);
    }
}
