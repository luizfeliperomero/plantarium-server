package com.plantarium.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    @Query(value = "select count(*) from garden\n" +
            "where garden.user_id = ?1", nativeQuery = true)
    Integer getGardenCountById(Integer userId);
}
