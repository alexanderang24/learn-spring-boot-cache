package com.example.demo.mapper;

import com.example.demo.dto.PeopleDto;
import com.example.demo.model.People;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PeopleMapper {

    List<PeopleDto> peopleListToPeopleDtoList(List<People> people);
    PeopleDto peopleToPeopleDto(People people);
    People peopleDtoToPeople(PeopleDto peopleDto);
}
