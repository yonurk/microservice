package com.example.user_microservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "eureka.client.enabled=false"
})
class UserCacheEvictIntegrationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRepository.save(new User(null, "John", "john@example.com"));
    }

    @Test
    void deletingUserClearsCachedList() {
        List<User> initial = userService.findAllUsersWithCache();
        assertEquals(1, initial.size());

        Long userId = initial.get(0).getId();
        userService.deleteUserById(userId);

        List<User> afterDeletion = userService.findAllUsersWithCache();
        assertTrue(afterDeletion.isEmpty());
    }
}
