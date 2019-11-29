package com.example.demo.mapper;

import com.example.demo.dto.PeopleDto;
import com.example.demo.model.People;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-29T16:19:51+0700",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
public class PeopleMapperImpl implements PeopleMapper {

    @Override
    public List<PeopleDto> peopleListToPeopleDtoList(List<People> people) {
        if ( people == null ) {
            return null;
        }

        List<PeopleDto> list = new ArrayList<PeopleDto>( people.size() );
        for ( People people1 : people ) {
            list.add( peopleToPeopleDto( people1 ) );
        }

        return list;
    }

    @Override
    public PeopleDto peopleToPeopleDto(People people) {
        if ( people == null ) {
            return null;
        }

        PeopleDto peopleDto = new PeopleDto();

        peopleDto.setId( people.getId() );
        peopleDto.setFirstName( people.getFirstName() );
        peopleDto.setLastName( people.getLastName() );

        return peopleDto;
    }

    @Override
    public People peopleDtoToPeople(PeopleDto peopleDto) {
        if ( peopleDto == null ) {
            return null;
        }

        People people = new People();

        people.setId( peopleDto.getId() );
        people.setFirstName( peopleDto.getFirstName() );
        people.setLastName( peopleDto.getLastName() );

        return people;
    }
}
