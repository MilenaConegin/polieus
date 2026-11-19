
package br.com.fiap.model;

public class Usuario {

    private Integer idUsuario;
    private String nomeUsuario;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;

    public Usuario() {

    }

    public Usuario(Integer idUsuario, String nomeUsuario, String cpf,
                   String telefone, String email, String senha) {
        setIdUsuario(idUsuario);
        setNomeUsuario(nomeUsuario);
        setCpf(cpf);
        setTelefone(telefone);
        setEmail(email);
        setSenha(senha);
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
