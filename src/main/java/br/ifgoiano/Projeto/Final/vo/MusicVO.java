package br.ifgoiano.Projeto.Final.vo;

import java.util.Date;

public class MusicVO {
    private Long id;
    private String nome;
    private String artista;
    private String fileName;
    private Boolean curtida;
    private Date createdAt;

    public MusicVO() {
        this.createdAt = new Date();
    }

    public MusicVO(Long id, String nome, String artista, String fileName, Boolean curtida) {
        this.id = id;
        this.nome = nome;
        this.artista = artista;
        this.fileName = fileName;
        this.curtida = curtida;
        this.createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean getCurtida() {
        return curtida;
    }

    public void setCurtida(Boolean curtida) {
        this.curtida = curtida;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    
}
