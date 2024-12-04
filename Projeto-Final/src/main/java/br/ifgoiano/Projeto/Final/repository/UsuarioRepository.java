package br.ifgoiano.Projeto.Final.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifgoiano.Projeto.Final.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByEmail(String email);
}
