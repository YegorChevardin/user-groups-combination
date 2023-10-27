package com.reactdev.projects.usercombinations.service.convertors;

import java.util.List;

/** Interface for converting entity to dto */
public interface EntityDtoConvertor<E, D> {
  /** Method that converts entity to dto */
  D convertEntityToDto(E entity);

  default List<D> convertEntityToDto(List<E> entities) {
    return entities.stream().map(this::convertEntityToDto).toList();
  }
}
