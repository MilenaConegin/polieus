
package br.com.fiap.bo;

import br.com.fiap.dao.AdministrativoDAO;
import br.com.fiap.exception.RecursoNaoEncontradoException;
import br.com.fiap.exception.RegraNegocioException;
import br.com.fiap.model.Administrativo;
import br.com.fiap.to.AdministrativoTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdministrativoBO {

    private final AdministrativoDAO administrativoDAO;
    private final ModelMapper modelMapper;

    public AdministrativoBO(AdministrativoDAO administrativoDAO, ModelMapper modelMapper) {
        this.administrativoDAO = administrativoDAO;
        this.modelMapper = modelMapper;
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

        Administrativo model = modelMapper.map(administrativo, Administrativo.class);

        Administrativo resultado = administrativoDAO.save(model);

        if (resultado == null) {
            throw new RegraNegocioException("Não foi possível cadastrar o administrador.");
        }

        return modelMapper.map(resultado, AdministrativoTO.class);
    }

    public ArrayList<AdministrativoTO> findAll() {
        ArrayList<Administrativo> administradores = administrativoDAO.findAll();
        ArrayList<AdministrativoTO> resultado = new ArrayList<>();

        for (Administrativo administrador : administradores) {
            AdministrativoTO to = modelMapper.map(administrador, AdministrativoTO.class);

            resultado.add(to);
        }

        return resultado;
    }

    public AdministrativoTO findById(Integer idAdmin) {

        if (idAdmin == null || idAdmin <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        Administrativo administrador = administrativoDAO.findById(idAdmin);

        if (administrador == null) {
            throw new RecursoNaoEncontradoException("Administrador não encontrado.");
        }

        AdministrativoTO resultado = modelMapper.map(administrador, AdministrativoTO.class);
        resultado.setSenha(null);

        return resultado;
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

        Administrativo model = modelMapper.map(administrativo, Administrativo.class);

        Administrativo resultado = administrativoDAO.update(model);

        if (resultado == null) {
            throw new RecursoNaoEncontradoException("Administrador não encontrado.");
        }

        AdministrativoTO to = modelMapper.map(resultado, AdministrativoTO.class);

        return to;
    }

    public AdministrativoTO login(AdministrativoTO administrador) {

        if (administrador == null || administrador.getEmail() == null || administrador.getEmail().isBlank() ||
                administrador.getSenha() == null || administrador.getSenha().isBlank()) {

            throw new IllegalArgumentException("Informe seu e-mail e sua senha.");
        }

        Administrativo resultado = administrativoDAO.login(
                administrador.getEmail().trim(),
                administrador.getSenha()
        );

        if (resultado == null) {
            throw new IllegalArgumentException("E-mail ou senha incorretos.");
        }

        return modelMapper.map(resultado, AdministrativoTO.class);
    }

}
