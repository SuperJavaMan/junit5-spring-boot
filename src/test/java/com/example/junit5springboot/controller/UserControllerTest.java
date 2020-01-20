package com.example.junit5springboot.controller;

import com.example.junit5springboot.Util.UserProviderUtil;
import com.example.junit5springboot.model.User;
import com.example.junit5springboot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Oleg Pavlyukov
 * on 05.01.2020
 * cpabox777@gmail.com
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserControllerTest {

    @InjectMocks
    @Autowired
    UserController controller;

    @Mock
    UserRepository repository;

    @Test
    void getAllUsers_ok() {
        List<User> requestUserList = UserProviderUtil.generateUserList(5);

        when(repository.findAll()).thenReturn(requestUserList);
        List<User> responseUserList = controller.getAllUsers();

        assertNotNull(responseUserList);
        assertEquals(responseUserList.size(), requestUserList.size());
        assertIterableEquals(responseUserList, requestUserList);
    }

    @Test
    void getUser() {
        User requestUser = UserProviderUtil.generateUser(1L, "Jack", 52);

        when(repository.findById(requestUser.getId())).thenReturn(Optional.of(requestUser));
        User responseUser = controller.getUser(requestUser.getId());

        assertNotNull(responseUser);
        assertEquals(responseUser.getId(), requestUser.getId());
        assertEquals(responseUser.getName(), requestUser.getName());
        assertEquals(responseUser.getAge(), requestUser.getAge());
    }

    @Test
    void addUser() {
        User requestUser = UserProviderUtil.generateUser(1L, "Nik", 40);

        when(repository.save(any(User.class))).thenReturn(requestUser);
        User responseUser = controller.addUser(requestUser);

        assertNotNull(responseUser);
        assertEquals(responseUser.getId(), requestUser.getId());
        assertEquals(responseUser.getName(), requestUser.getName());
        assertEquals(responseUser.getAge(), requestUser.getAge());
    }

    @Test
    void updateUser() {
        User requestUser = UserProviderUtil.generateUser(1L, "Alex", 40);

        when(repository.save(any(User.class))).thenReturn(requestUser);
        User responseUser = controller.addUser(requestUser);

        assertNotNull(responseUser);
        assertEquals(responseUser.getId(), requestUser.getId());
        assertEquals(responseUser.getName(), requestUser.getName());
        assertEquals(responseUser.getAge(), requestUser.getAge());
    }

    @Test
    void deleteUser() {
        Long userId = 1L;

        ResponseEntity<String> responseEntity = controller.deleteUser(userId);
        String message = responseEntity.getBody();

        assertNotNull(responseEntity);
        assertNotNull(message);
        assertEquals(message, "User deleted successfully!");
    }
}