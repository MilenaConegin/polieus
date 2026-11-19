
package br.com.fiap.model;

public class Administrativo {

    private Integer idAdmin;
    private String nomeAdmin;
    private String email;
    private String instituicao;
    private String cargo;
    private String cdConvite;
    private String senha;

    public Administrativo() {

    }

    public Administrativo(Integer idAdmin, String nomeAdmin, String email,
                          String instituicao, String cargo, String cdConvite, String senha) {
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
