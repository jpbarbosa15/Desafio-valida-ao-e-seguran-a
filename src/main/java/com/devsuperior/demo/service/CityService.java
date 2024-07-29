package com.devsuperior.demo.service;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        List<City> list = repository.findAll();
        list.sort((o1, o2) ->o1.getName().compareTo(o2.getName()));
        List<CityDTO> listDTO = list.stream().map(x ->new CityDTO(x)).toList();
        return listDTO;
    }

    @Transactional
    public CityDTO insert(CityDTO dto) {
        City city = new City();
        city.setName(dto.getName());
        repository.save(city);
        return new CityDTO(city);
    }


}
