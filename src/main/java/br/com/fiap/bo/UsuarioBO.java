
package br.com.fiap.bo;

import br.com.fiap.model.Usuario;
import org.modelmapper.ModelMapper;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.exception.RecursoNaoEncontradoException;
import br.com.fiap.to.UsuarioTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioBO {

    private final UsuarioDAO usuarioDAO;
    private final ModelMapper modelMapper;

    public UsuarioBO(UsuarioDAO usuarioDAO, ModelMapper modelMapper) {
        this.usuarioDAO = usuarioDAO;
        this.modelMapper = modelMapper;
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

        Usuario model = modelMapper.map(usuario, Usuario.class);

        Usuario salvo = usuarioDAO.save(model);

        return converterParaTO(salvo);
    }

    public ArrayList<UsuarioTO> findAll() {
        ArrayList<Usuario> usuarios = usuarioDAO.findAll();
        ArrayList<UsuarioTO> usuariosTO = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            usuariosTO.add(converterParaTO(usuario));
        }

        return usuariosTO;
    }

    public UsuarioTO findById(Integer idUsuario) {

        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        Usuario usuario = usuarioDAO.findById(idUsuario);

        if (usuario == null) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado!");
        }

        return converterParaTO(usuario);
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

        Usuario model = modelMapper.map(usuario, Usuario.class);

        Usuario atualizado = usuarioDAO.update(model);

        if (atualizado == null) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado!");
        }

        return converterParaTO(atualizado);

    }

    public UsuarioTO login(UsuarioTO usuario) {

        if (usuario == null || usuario.getEmail() == null || usuario.getEmail().isBlank() ||
                usuario.getSenha() == null || usuario.getSenha().isBlank()) {

            throw new IllegalArgumentException("Informe seu e-mail e sua senha.");
        }

        Usuario resultado = usuarioDAO.login(
                usuario.getEmail().trim(),
                usuario.getSenha()
        );

        if (resultado == null) {
            throw new IllegalArgumentException("E-mail ou senha incorretos.");
        }

        return converterParaTO(resultado);
    }

    private UsuarioTO converterParaTO(Usuario usuario) {

        UsuarioTO usuarioTO = modelMapper.map(usuario, UsuarioTO.class);

        return usuarioTO;
    }

}
