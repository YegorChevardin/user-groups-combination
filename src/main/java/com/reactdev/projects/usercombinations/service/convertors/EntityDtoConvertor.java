package com.reactdev.projects.usercombinations.service.convertors;

/** Interface for converting entity to dto */
public interface EntityDtoConvertor<E, D> {
  /** Method that converts entity to dto */
  D convertEntityToDto(E entity);

  E convertDtoToEntity(D dto);
}
