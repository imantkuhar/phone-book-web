package com.cooksdev.service.util.transformer.base;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractTransformer<ENTITY, DTO> {

    public abstract DTO convertToDto(ENTITY entity);

    public abstract ENTITY convertToEntity(DTO dto);

    public List<DTO> convertToDto(Collection<ENTITY> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }
        return entities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<ENTITY> convertToEntity(Collection<DTO> dtos) {
        if (CollectionUtils.isEmpty(dtos)) {
            return Collections.emptyList();
        }
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
