package br.com.fiap.to;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AdministrativoTO {
    private Integer idAdmin;

    @NotBlank   
    private String nomeAdmin;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String instituicao;

    @NotBlank
    private String cargo;

    @NotBlank
    private String cdConvite;

    @NotBlank
    @Size(min = 8, max = 100)
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_]).*$",
            message = "A senha deve conter pelo menos um número, uma letra maiúscula, uma letra minúscula e um caractere especial"
    )
    private String senha;

    public AdministrativoTO(){

    }

    public AdministrativoTO(Integer idAdmin, String nomeAdmin, String email, String instituicao, String cargo, String cdConvite, String senha) {
        setIdAdmin(idAdmin);
        setNomeAdmin(nomeAdmin);
        setEmail(email);
        setInstituicao(instituicao);
        setCargo(cargo);
        setCdConvite(cdConvite);
        setSenha(senha);
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNomeAdmin() {
        return nomeAdmin;
    }

    public void setNomeAdmin(String nomeAdmin) {
        this.nomeAdmin = nomeAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCdConvite() {
        return cdConvite;
    }

    public void setCdConvite(String cdConvite) {
        this.cdConvite = cdConvite;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
