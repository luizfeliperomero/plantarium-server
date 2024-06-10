package com.plantarium.plant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {
    @Query(value = "SELECT * FROM plant WHERE plant.garden_id = ?1", nativeQuery = true)
    List<Plant> findAllByUserId(Long gardenId);
}
