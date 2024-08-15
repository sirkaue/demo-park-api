package com.sirkaue.demoparkapi.web.dto.mapper;

import com.sirkaue.demoparkapi.entity.ClienteVaga;
import com.sirkaue.demoparkapi.web.dto.EstacionamentoCreateDto;
import com.sirkaue.demoparkapi.web.dto.EstacionamentoResponseDto;
import org.modelmapper.ModelMapper;

public class ClienteVagaMapper {

    private ClienteVagaMapper() {
    }

    public static ClienteVaga toClienteVaga(EstacionamentoCreateDto dto) {
        return new ModelMapper().map(dto, ClienteVaga.class);
    }

    public static EstacionamentoResponseDto toDto(ClienteVaga clienteVaga) {
        return new ModelMapper().map(clienteVaga, EstacionamentoResponseDto.class);
    }
}
