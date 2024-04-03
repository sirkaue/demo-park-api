package com.sirkaue.demoparkapi.repositories;


import com.sirkaue.demoparkapi.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
