package br.ifgoiano.Projeto.Final.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifgoiano.Projeto.Final.mapper.DozerMapper;
import br.ifgoiano.Projeto.Final.model.Usuario;
import br.ifgoiano.Projeto.Final.services.UsuarioService;
import br.ifgoiano.Projeto.Final.vo.UsuarioVO;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioVO> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        return DozerMapper.parseListObjects(usuarios, UsuarioVO.class);
    }

    @GetMapping("/{id}")
    public UsuarioVO findById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return DozerMapper.parseObject(usuario, UsuarioVO.class);
    }

    @PostMapping
    public UsuarioVO create(@RequestBody UsuarioVO usuario) {
        Usuario usuarioEntity = DozerMapper.parseObject(usuario, Usuario.class);
        return DozerMapper.parseObject(usuarioService.create(usuarioEntity), UsuarioVO.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}
