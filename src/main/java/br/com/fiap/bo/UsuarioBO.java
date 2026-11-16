
package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

import java.util.ArrayList;

public class UsuarioBO {

    UsuarioDAO usuarioDAO;

    public UsuarioTO save(UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();

        if (usuario.getCpf() == null || !usuario.getCpf().matches("^\\d{11}$")) {
            throw new IllegalArgumentException("O CPF deve conter exatamente 11 números.");
        }

        if (usuario.getEmail() == null || !usuario.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Informe um e-mail válido.");
        }

        if (usuario.getSenha() == null || !usuario.getSenha().matches(
                        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_]).{8,100}$")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos 8 caracteres, um número, uma letra maiúscula, uma letra minúscula e um caractere especial.");
        }

        return usuarioDAO.save(usuario);
    }

    public ArrayList<UsuarioTO> findAll() {

        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findAll();
    }

    public UsuarioTO findById(Integer idUsuario) {

        usuarioDAO = new UsuarioDAO();

        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        return usuarioDAO.findById(idUsuario);
    }

    public boolean delete(Integer idUsuario) {
        usuarioDAO = new UsuarioDAO();

        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        return usuarioDAO.delete(idUsuario);
    }

    public UsuarioTO update(UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();

        if (usuario.getCpf() == null || !usuario.getCpf().matches("^\\d{11}$")) {
            throw new IllegalArgumentException("O CPF deve conter exatamente 11 números.");
        }

        if (usuario.getEmail() == null || !usuario.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Informe um e-mail válido.");
        }

        if (usuario.getSenha() == null || !usuario.getSenha().matches(
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_]).{8,100}$")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos 8 caracteres, um número, uma letra maiúscula, uma letra minúscula e um caractere especial.");
        }

        return usuarioDAO.update(usuario);
    }
}
