package com.sirkaue.demoparkapi.service.impl;

import com.sirkaue.demoparkapi.entity.Vaga;
import com.sirkaue.demoparkapi.exception.CodigoUniqueViolationException;
import com.sirkaue.demoparkapi.exception.EntityNotFoundException;
import com.sirkaue.demoparkapi.repository.VagaRepository;
import com.sirkaue.demoparkapi.service.VagaService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sirkaue.demoparkapi.entity.Vaga.StatusVaga.LIVRE;

@Service
public class VagaServiceImpl implements VagaService {

    private final VagaRepository vagaRepository;

    public VagaServiceImpl(VagaRepository vagaRepository) {
        this.vagaRepository = vagaRepository;
    }

    @Override
    @Transactional
    public Vaga salvar(Vaga vaga) {
        try {
            return vagaRepository.save(vaga);
        } catch (DataIntegrityViolationException e) {
            throw new CodigoUniqueViolationException(
                    String.format("Vaga com códido '%s' já cadastrada", vaga.getCodigo()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Vaga buscarPorCodigo(String codigo) {
        return vagaRepository.findByCodigo(codigo).orElseThrow(
                () -> new EntityNotFoundException(String.format("Vaga com código '%s' não foi encontrado", codigo)));
    }

    @Override
    @Transactional(readOnly = true)
    public Vaga buscarPorVagaLivre() {
        return vagaRepository.findFirstByStatus(LIVRE).orElseThrow(
                () -> new EntityNotFoundException("Nenhuma vaga livre foi encontrada")
        );
    }
}
