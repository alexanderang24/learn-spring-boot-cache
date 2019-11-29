package com.example.demo.service;

import com.example.demo.dto.PeopleDto;
import com.example.demo.mapper.PeopleMapper;
import com.example.demo.model.People;
import com.example.demo.repository.PeopleRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PeopleService {

    @Autowired
    PeopleRepository peopleRepository;

    private PeopleMapper peopleMapper = Mappers.getMapper(PeopleMapper.class);

    @Cacheable(cacheNames = "findAllCache")
    public List<PeopleDto> findAll() {
        log.info("Connecting to DB...");
        return peopleMapper.peopleListToPeopleDtoList(peopleRepository.findAll());
    }

    @Caching(evict = {
            @CacheEvict(value = "findAllCache", allEntries = true),
            @CacheEvict(value = "findByIdCache", allEntries = true)})
    public PeopleDto savePeople(PeopleDto peopleDto) {
        if(peopleDto.getId() == null) {
            log.info("Inserting new people...");
            return peopleMapper.peopleToPeopleDto(peopleRepository.save(peopleMapper.peopleDtoToPeople(peopleDto)));
        } else {
            log.info("Updating people...");
            if(findById(peopleDto.getId()) != null) {
                return peopleMapper.peopleToPeopleDto(peopleRepository.save(peopleMapper.peopleDtoToPeople(peopleDto)));
            } else {
                log.error("Failed to save or update");
                return null;
            }
        }
    }

    @Cacheable(cacheNames = "findByIdCache")
    public PeopleDto findById(Long id) {
        log.info("Connecting to DB...");
        Optional<People> findPeople = peopleRepository.findById(id);
        if(findPeople.isPresent()) {
            return peopleMapper.peopleToPeopleDto(findPeople.get());
        } else {
            log.error("People not found");
            return null;
        }
    }

    @Autowired
    CacheManager cacheManager;

    public void flushCache() {
        for (String cacheName : cacheManager.getCacheNames()) {
            cacheManager.getCache(cacheName).clear();
            log.info("Flushing cache with name: " + cacheName);
        }
    }
}
