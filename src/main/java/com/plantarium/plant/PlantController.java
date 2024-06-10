package com.plantarium.plant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plant")
public class PlantController {
    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @PostMapping("/save")
    public ResponseEntity<Plant> save(Plant plant) {
        return ResponseEntity.ok(this.plantService.save(plant));
    }

    @GetMapping("/findAll/{userId}")
    public ResponseEntity<List<Plant>> findAllByUserId(@PathVariable Long userId) {
       return ResponseEntity.ok(this.plantService.getPlants(userId));
    }

}
