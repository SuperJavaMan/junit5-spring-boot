package com.example.junit5springboot.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * @author Oleg Pavlyukov
 * on 30.12.2019
 * cpabox777@gmail.com
 */
@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllByName() {
        assertNotNull(repository);
        assertNotNull(repository.findAllByName("Joe"));
        assertEquals(repository.findAllByName("Joe").get(0).getName(), "Joe");
    }
}
