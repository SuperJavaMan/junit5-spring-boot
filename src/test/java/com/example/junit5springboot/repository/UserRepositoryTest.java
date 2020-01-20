package com.example.junit5springboot.repository;

import com.example.junit5springboot.Util.UserProviderUtil;
import com.example.junit5springboot.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * @author Oleg Pavlyukov
 * on 04.01.2020
 * cpabox777@gmail.com
 */
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    private List<User> userList;

    @BeforeEach
    void setUp() {
        userList = UserProviderUtil.generateUserList(5);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void findAllByName_retrieve_entity_from_db_ok() {
        User user = userList.get(0);
        System.out.println(user.getName());
        assertNotNull(repository);
        assertNotNull(repository.findAllByName(user.getName()));
        assertEquals(repository.findAllByName(user.getName()).get(0).getName(), user.getName());
    }

    @Test
    void find_top_by_age_ok() {

    }
}
