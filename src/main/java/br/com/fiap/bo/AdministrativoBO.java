
package br.com.fiap.bo;

import br.com.fiap.dao.AdministrativoDAO;
import br.com.fiap.exception.RecursoNaoEncontradoException;
import br.com.fiap.exception.RegraNegocioException;
import br.com.fiap.to.AdministrativoTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdministrativoBO {

    private final AdministrativoDAO administrativoDAO;

    public AdministrativoBO(AdministrativoDAO administrativoDAO) {
        this.administrativoDAO = administrativoDAO;
    }

    public AdministrativoTO save(AdministrativoTO administrativo) {

        if (administrativo == null) {
            throw new IllegalArgumentException("Informe os dados do administrador.");
        }

        if (administrativo.getEmail() == null || !administrativo.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Informe um e-mail válido.");
        }

        if (administrativo.getSenha() == null || !administrativo.getSenha().matches(
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_]).{8,100}$")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos 8 caracteres, um número, uma letra maiúscula, uma letra minúscula e um caractere especial.");
        }

        AdministrativoTO resultado = administrativoDAO.save(administrativo);

        if (resultado == null) {
            throw new RegraNegocioException("Não foi possível cadastrar o administrador.");
        }

        return resultado;

    }

    public ArrayList<AdministrativoTO> findAll() {
        return administrativoDAO.findAll();
    }

    public AdministrativoTO findById(Integer idAdmin) {

        if (idAdmin == null || idAdmin <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        AdministrativoTO administrador = administrativoDAO.findById(idAdmin);

        if (administrador == null) {
            throw new RecursoNaoEncontradoException("Administrador não encontrado.");
        }

        return administrador;
    }

    public boolean delete(Integer idAdmin) {

        if (idAdmin == null || idAdmin <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        boolean resultado = administrativoDAO.delete(idAdmin);

        if (!resultado) {
            throw new RecursoNaoEncontradoException("Administrador não encontrado.");
        }

        return true;
    }

    public AdministrativoTO update(AdministrativoTO administrativo) {

        if (administrativo.getIdAdmin() == null || administrativo.getIdAdmin() <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        if (administrativo.getEmail() == null || !administrativo.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Informe um e-mail válido.");
        }

        if (administrativo.getSenha() == null || !administrativo.getSenha().matches(
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_]).{8,100}$")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos 8 caracteres, um número, uma letra maiúscula, uma letra minúscula e um caractere especial.");
        }

        AdministrativoTO resultado = administrativoDAO.update(administrativo);

        if (resultado == null) {
            throw new RecursoNaoEncontradoException("Administrador não encontrado.");
        }

        return resultado;
    }

    public AdministrativoTO login(AdministrativoTO administrador) {

        if (administrador == null || administrador.getEmail() == null || administrador.getEmail().isBlank() ||
                administrador.getSenha() == null || administrador.getSenha().isBlank()) {

            throw new IllegalArgumentException("Informe seu e-mail e sua senha.");
        }

        AdministrativoTO resultado = administrativoDAO.login(administrador.getEmail().trim(), administrador.getSenha());
        if (resultado == null) {
            throw new IllegalArgumentException("E-mail ou senha incorretos.");
        }

        return resultado;
    }

}
