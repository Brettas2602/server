package br.ifgoiano.Projeto.Final.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifgoiano.Projeto.Final.model.Music;
import br.ifgoiano.Projeto.Final.repository.MusicRepository;

@Service
public class MusicService {
    @Autowired
    private MusicRepository musicRepository;

    public List<Music> findAll() {
        return musicRepository.findAllByOrderByIdAsc();
    }

    public List<Music> findAllByNome(String nome) {
        return musicRepository.findAllByNome(nome);
    }

    public Music findById(Long id) {
        return musicRepository.findById(id).orElse(null);
    }

    public List<Music> findByCurtida(Boolean curtida) {
        return musicRepository.findAllByCurtidaOrderByIdAsc(curtida);
    }

    public Music put(Music music) {
        return musicRepository.save(music);
    }

    public Music create(Music music) {
        return musicRepository.save(music);
    }

    public void delete(Long id) {
        musicRepository.deleteById(id);
    }
}
