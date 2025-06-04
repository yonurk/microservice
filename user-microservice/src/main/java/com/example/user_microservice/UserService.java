package com.example.user_microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @CacheEvict(value = "users", key = "#id")
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    // Caching-enabled getAll method
    @Cacheable("users")
    public List<User> findAllUsersWithCache() {
        try {
            Thread.sleep(2000); // simulate time-consuming DB access
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userRepository.findAll();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
