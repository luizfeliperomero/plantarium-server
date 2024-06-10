package com.plantarium.garden;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Garden {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="garden_seq")
    @SequenceGenerator(name="garden_seq", sequenceName="garden_seq", allocationSize=1)
    Integer gardenId;
    String gardenName;
    String gardenAddress;
    String gardenLatitude;
    String gardenLongitude;
    String gardenProfilePhotoUrl;
    Integer userId;
}
