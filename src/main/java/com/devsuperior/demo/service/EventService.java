package com.devsuperior.demo.service;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;
    @Autowired
    private CityRepository cityRepository;


    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        Page<Event> page = repository.findAll(pageable);
        return page.map(event -> new EventDTO(event));
    }

    public EventDTO insert(EventDTO dto) {
        Event event = new Event();
        copyDTOToEntity(dto,event);
        event = repository.save(event);
        return new EventDTO(event);
    }

    private void copyDTOToEntity(EventDTO dto, Event event) {
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setUrl(dto.getUrl());
        City city = new City();
        city = cityRepository.getOne(dto.getCityId());
        event.setCity(city);
    }
}
