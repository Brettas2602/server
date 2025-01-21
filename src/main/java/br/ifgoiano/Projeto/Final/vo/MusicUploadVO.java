package br.ifgoiano.Projeto.Final.vo;

public class MusicUploadVO {
    private String name;
    private String artista;
    private Boolean curtida;
    private FileVO file;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public FileVO getFile() {
        return file;
    }
    public void setFile(FileVO file) {
        this.file = file;
    }
    public String getArtista() {
        return artista;
    }
    public void setArtista(String artista) {
        this.artista = artista;
    }
    public Boolean getCurtida() {
        return curtida;
    }
    public void setCurtida(Boolean curtida) {
        this.curtida = curtida;
    }

    
}
