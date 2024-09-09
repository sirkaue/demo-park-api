package com.sirkaue.demoparkapi.repository;

import com.sirkaue.demoparkapi.entity.ClienteVaga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteVagaRepository extends JpaRepository<ClienteVaga, Long> {

    Optional<ClienteVaga> findByReciboAndDataSaidaIsNull(String recibo);

    long countByClienteCpfAndDataSaidaIsNotNull(String cpf);

    <T> Page<T> findAllByClienteCpf(String cpf, Pageable pageable);

    <T> Page<T> findAllByClienteUsuarioId(Long id, Pageable pageable);
}
