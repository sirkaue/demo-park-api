package com.sirkaue.demoparkapi.repository;

import com.sirkaue.demoparkapi.entity.Cliente;
import com.sirkaue.demoparkapi.repository.projection.ClienteProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c")
    Page<ClienteProjection> findAllPageable(Pageable pageable);

    Cliente findByUsuarioId(Long id);

    Optional<Cliente> findByCpf(String cpf);
}
