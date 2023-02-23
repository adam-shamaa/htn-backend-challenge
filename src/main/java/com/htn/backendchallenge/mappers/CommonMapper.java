package com.htn.backendchallenge.mappers;

import com.htn.apicodegen.model.FieldErrorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.validation.FieldError;

@Mapper
public interface CommonMapper {
  @Mapping(target = "message", source = "defaultMessage")
  FieldErrorDto fieldErrorToFieldErrorDto(FieldError error);
}
