package com.plantarium.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    Integer getGardenCountById(Integer userId) {
        return this.userRepository.getGardenCountById(userId);
    }
}