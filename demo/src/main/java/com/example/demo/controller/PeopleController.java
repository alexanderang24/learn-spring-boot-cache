package com.example.demo.controller;

import com.example.demo.dto.PeopleDto;
import com.example.demo.service.PeopleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @GetMapping(value = "/all")
    public List<PeopleDto> findAllPeople() {
        return peopleService.findAll();
    }

    @GetMapping(value = "/id")
    public PeopleDto findPeopleById(@RequestBody PeopleDto peopleRequest) {
        return peopleService.findById(peopleRequest.getId());
    }

    @PostMapping(value = "/update")
    public PeopleDto updatePeople(@RequestBody PeopleDto peopleRequest) {
        return peopleService.savePeople(peopleRequest);
    }

    @PostMapping(value = "/flush")
    public void flushCache() {
        peopleService.flushCache();
    }
}
