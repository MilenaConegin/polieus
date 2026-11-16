
package br.com.fiap.bo;

import br.com.fiap.dao.AutenticacaoUsuarioDAO;
import br.com.fiap.to.AutenticacaoUsuarioTO;

import java.util.ArrayList;

public class AutenticacaoUsuarioBO {

    AutenticacaoUsuarioDAO autenticacaoUsuarioDAO;

    public AutenticacaoUsuarioTO save(AutenticacaoUsuarioTO autenticacao) {
        autenticacaoUsuarioDAO = new AutenticacaoUsuarioDAO();

        if (autenticacao.getEmail() == null || !autenticacao.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Informe um e-mail válido.");
        }

        if (autenticacao.getSenha() == null || !autenticacao.getSenha().matches(
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_]).{8,100}$")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos 8 caracteres, um número, uma letra maiúscula, uma letra minúscula e um caractere especial.");
        }

        return autenticacaoUsuarioDAO.save(autenticacao);
    }

    public ArrayList<AutenticacaoUsuarioTO> findAll() {
        autenticacaoUsuarioDAO = new AutenticacaoUsuarioDAO();
        return autenticacaoUsuarioDAO.findAll();
    }

    public AutenticacaoUsuarioTO findById(Integer idAutenticacao) {
        autenticacaoUsuarioDAO = new AutenticacaoUsuarioDAO();

        if (idAutenticacao == null || idAutenticacao <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        return autenticacaoUsuarioDAO.findById(idAutenticacao);
    }

    public boolean delete(Integer idAutenticacao) {
        autenticacaoUsuarioDAO = new AutenticacaoUsuarioDAO();

        if (idAutenticacao == null || idAutenticacao <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        return autenticacaoUsuarioDAO.delete(idAutenticacao);
    }

    public AutenticacaoUsuarioTO update(AutenticacaoUsuarioTO autenticacao) {
        autenticacaoUsuarioDAO = new AutenticacaoUsuarioDAO();

        if (autenticacao.getEmail() == null ||
                !autenticacao.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Informe um e-mail válido.");
        }

        if (autenticacao.getSenha() == null || !autenticacao.getSenha().matches(
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_]).{8,100}$")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos 8 caracteres, um número, uma letra maiúscula, uma letra minúscula e um caractere especial.");
        }

        return autenticacaoUsuarioDAO.update(autenticacao);
    }
}
