package com.plantarium.garden;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GardenService {
    private final GardenRepository gardenRepository;

    public GardenService(GardenRepository gardenRepository) {
        this.gardenRepository = gardenRepository;
    }

    public void save(Garden garden) {
       this.gardenRepository.save(garden);
    }
    public List<Garden> findAllByUserId(Long id) {
        return this.gardenRepository.findAllByUserId(id);
    }
}
