package com.plantarium.plant;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Plant {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="plant_seq")
    @SequenceGenerator(name="plant_seq", sequenceName="plant_seq", allocationSize=1)
    @Column(name = "plant_id")
    Long id;
    @Column(name = "plant_notes")
    String notes;
    @JsonProperty("garden_id")
    Long gardenId;
    @JsonProperty("watering")
    String wateringInterval;
    @JsonProperty("popular_name")
    String popularName;
    @JsonProperty("scientific_name")
    String scientificName;
}
