package com.reactdev.projects.usercombinations.service.convertors;

import java.util.List;

/**
 * Interface for converting dto objects back to entity
 *
 * @version 0.0.1
 */
public interface ReverseEntityDtoConvertor<E, D> extends EntityDtoConvertor<E, D> {
  E dtoToEntity(D dto);

  default List<E> dtoToEntity(List<D> dtos) {
    return dtos.stream().map(this::dtoToEntity).toList();
  }
}
