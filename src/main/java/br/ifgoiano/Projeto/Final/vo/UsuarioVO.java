package br.ifgoiano.Projeto.Final.vo;

import java.util.Date;

public class UsuarioVO {
    private Long id;
    private String nomeDeUsuario;
    private String email;
    private String senha;
    private Date createdAt;

    public UsuarioVO() {
        this.createdAt = new Date();
    }

    public UsuarioVO(Long id, String nomeDeUsuario, String email, String senha) {
        this.id = id;
        this.nomeDeUsuario = nomeDeUsuario;
        this.email = email;
        this.senha = senha;
        this.createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
