package com.sirkaue.demoparkapi.repository;

import com.sirkaue.demoparkapi.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
}
