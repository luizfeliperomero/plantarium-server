package com.plantarium.plant;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {
    private final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public Plant save(Plant plant) {
       return this.plantRepository.save(plant);
    }

    public List<Plant> getPlants(Long gardenId) {
        return this.plantRepository.findAllByUserId(gardenId);
    }
}
