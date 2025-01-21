package br.ifgoiano.Projeto.Final.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="musics")
public class Music {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable=false, length = 255)
    private String nome;

    @Column(name = "artista", nullable = false, length = 255)
    private String artista;

    @Column(name = "filename", nullable = false, length=255)
    private String fileName;

    @Column(name="curtida", nullable=false)
    private Boolean curtida;

    @Column(name="created_at", nullable=false)
    private Date createdAt;

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
