package com.sirkaue.demoparkapi.service;

import com.sirkaue.demoparkapi.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario salvar(Usuario usuario);

    Usuario buscarPorId(Long id);

    Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha);

    List<Usuario> buscarTodos();

    Usuario buscarPorUsername(String username);

    Usuario.Role buscarRolePorUsername(String username);
}
