package com.sirkaue.demoparkapi.service.impl;

import com.sirkaue.demoparkapi.entity.Usuario;
import com.sirkaue.demoparkapi.exception.PasswordInvalidException;
import com.sirkaue.demoparkapi.exception.UsernameNotFoundException;
import com.sirkaue.demoparkapi.exception.UsernameUniqueViolationException;
import com.sirkaue.demoparkapi.exception.UsuarioNotFoundException;
import com.sirkaue.demoparkapi.repository.UsuarioRepository;
import com.sirkaue.demoparkapi.service.UsuarioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Usuario salvar(Usuario usuario) {
        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException("e-mail", usuario.getUsername());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    @Override
    @Transactional
    public void editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
        if (!novaSenha.equals(confirmaSenha)) {
            throw new PasswordInvalidException("exception.PasswordInvalidException.confirmation");
        }

        Usuario user = buscarPorId(id);
        if (!passwordEncoder.matches(senhaAtual, user.getPassword())) {
            throw new PasswordInvalidException("exception.PasswordInvalidException.current");
        }

        if (passwordEncoder.matches(novaSenha, user.getPassword())) {
            throw new PasswordInvalidException("exception.PasswordInvalidException.same");
        }

        user.setPassword(passwordEncoder.encode(novaSenha));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username));
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario.Role buscarRolePorUsername(String username) {
        return usuarioRepository.findRoleByUsername(username);
    }
}