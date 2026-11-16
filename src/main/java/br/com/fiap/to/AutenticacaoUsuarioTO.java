package br.com.fiap.to;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AutenticacaoUsuarioTO {
    private Integer idAutenticacao;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 100)
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_]).*$",
            message = "A senha deve conter pelo menos um número, uma letra maiúscula, uma letra minúscula e um caractere especial"
    )
    private String senha;

    public AutenticacaoUsuarioTO(){

    }

    public AutenticacaoUsuarioTO(Integer idAutenticacao, String email, String senha) {
        setIdAutenticacao(idAutenticacao);
        setEmail(email);
        setSenha(senha);
    }

    public Integer getIdAutenticacao() {
        return idAutenticacao;
    }

    public void setIdAutenticacao(Integer idAutenticacao) {
        this.idAutenticacao = idAutenticacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
