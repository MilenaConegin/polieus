
package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.exception.RecursoNaoEncontradoException;
import br.com.fiap.to.UsuarioTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioBO {

    private final UsuarioDAO usuarioDAO;

    public UsuarioBO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public UsuarioTO save(UsuarioTO usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException("Informe os dados do usuário.");
        }

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
        return usuarioDAO.findAll();
    }

    public UsuarioTO findById(Integer idUsuario) {

        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        UsuarioTO usuario = usuarioDAO.findById(idUsuario);

        if (usuario == null) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado!");
        }

        return usuario;
    }

    public boolean delete(Integer idUsuario) {

        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        boolean excluido = usuarioDAO.delete(idUsuario);

        if (!excluido) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado!");
        }

        return true;
    }

    public UsuarioTO update(UsuarioTO usuario) {


        if (usuario.getIdUsuario() == null || usuario.getIdUsuario() <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

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

    public UsuarioTO login(UsuarioTO usuario) {

        if (usuario == null || usuario.getEmail() == null || usuario.getEmail().isBlank() ||
                usuario.getSenha() == null || usuario.getSenha().isBlank()) {

            throw new IllegalArgumentException("Informe seu e-mail e sua senha.");
        }

        UsuarioTO resultado = usuarioDAO.login(usuario.getEmail().trim(), usuario.getSenha());
        if (resultado == null) {
            throw new IllegalArgumentException("E-mail ou senha incorretos.");
        }

        return resultado;
    }
}
