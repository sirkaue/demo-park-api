package com.sirkaue.demoparkapi.web.dto.mapper;

import com.sirkaue.demoparkapi.web.dto.PageableDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class PageableMapper {

    private PageableMapper() {
    }

    public static PageableDto toDto(Page page) {
        return new ModelMapper().map(page, PageableDto.class);
    }
}
