package com.plantarium.garden;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/garden")
public class GardenController {
    private final GardenService gardenService;

    public GardenController(GardenService gardenService) {
        this.gardenService = gardenService;
    }

    @PostMapping("/save")
    public ResponseEntity save(Garden garden) {
       this.gardenService.save(garden);
       return ResponseEntity.ok("");
    }

    @GetMapping("/findAllByUserId/{userId}")
    public ResponseEntity findAll(@PathVariable Long userId) {
        return ResponseEntity.ok(gardenService.findAllByUserId(userId));
    }
}
