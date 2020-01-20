package com.example.junit5springboot;

import com.example.junit5springboot.controller.UserController;
import com.example.junit5springboot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class Junit5SpringBootApplicationTests {

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
        assertNotNull(userController);
        assertNotNull(userRepository);
    }

}
