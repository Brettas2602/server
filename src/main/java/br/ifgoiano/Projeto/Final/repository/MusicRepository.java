package br.ifgoiano.Projeto.Final.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifgoiano.Projeto.Final.model.Music;

public interface MusicRepository extends JpaRepository<Music, Long>{
    public List<Music> findAllByArtista(String artista);

    public List<Music> findAllByNome(String nome);
}
