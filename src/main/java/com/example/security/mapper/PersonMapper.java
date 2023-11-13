package com.example.security.mapper;

import com.example.security.controller.dto.response.PersonResponseDto;
import com.example.security.domain.entity.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonResponseDto toResponseDto(Person person);

    List<PersonResponseDto> responseList(List<Person> listPerson);
}
