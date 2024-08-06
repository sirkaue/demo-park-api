package com.sirkaue.demoparkapi.web.dto.mapper;

import com.sirkaue.demoparkapi.web.dto.PageableDto;
import org.springframework.data.domain.Page;

public class PageableMapper {

    private PageableMapper() {
    }

    public static <T> PageableDto<T> toDto(Page<T> page) {
        PageableDto<T> dto = new PageableDto<>();
        dto.setContent(page.getContent());
        dto.setNumber((page.getNumber()));
        dto.setSize(page.getSize());
        dto.setTotalElements(page.getTotalElements());
        dto.setTotalPages(page.getTotalPages());
        return dto;
    }
}
