package com.sirkaue.demoparkapi.web.dto.mapper;

import com.sirkaue.demoparkapi.entity.Vaga;
import com.sirkaue.demoparkapi.web.dto.VagaCreateDto;
import com.sirkaue.demoparkapi.web.dto.VagaResponseDto;
import org.modelmapper.ModelMapper;

public class VagaMapper {

    public static Vaga toVaga(VagaCreateDto dto) {
        return new ModelMapper().map(dto, Vaga.class);
    }

    public static VagaResponseDto toDto(Vaga vaga) {
        return new ModelMapper().map(vaga, VagaResponseDto.class);
    }

    private VagaMapper() {
    }
}
