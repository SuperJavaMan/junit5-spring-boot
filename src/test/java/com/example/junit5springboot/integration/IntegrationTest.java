package com.example.junit5springboot.integration;

import com.example.junit5springboot.model.User;
import com.example.junit5springboot.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Oleg Pavlyukov
 * on 04.01.2020
 * cpabox777@gmail.com
 */
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserRepository repository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Nik");
        user.setAge(80);
        repository.save(user);
    }

    @AfterEach
    void tearDown() {
        repository.delete(user);
    }

    @Test
    void getAllUsers() throws URISyntaxException {
        ResponseEntity<User[]> response = restTemplate.getForEntity(getUri(), User[].class);
        assertNotNull(response.getBody());
    }

    @Test
    void getUser() throws URISyntaxException {
        Long userId = user.getId();
        ResponseEntity<User> response = restTemplate.getForEntity(getUri(String.valueOf(userId)), User.class);
        User responseUser = response.getBody();

        assertNotNull(response);
        assertNotNull(responseUser);
        assertNotNull(responseUser.getName());
        assertEquals(responseUser.getName(), user.getName());
    }

    @Test
    void addUser() throws URISyntaxException {
        User user = new User();
        user.setName("Test User");
        user.setAge(20);

        ResponseEntity<User> responseEntity = restTemplate.postForEntity(getUri(), user, User.class);
        User responseUser = responseEntity.getBody();

        assertNotNull(responseEntity);
        assertNotNull(responseUser);
        assertNotNull(responseUser.getName());
        assertEquals(responseUser.getName(), user.getName());
        assertEquals(responseUser.getAge(), user.getAge());
    }

    @Test
    void updateUser() throws URISyntaxException {
        User requestUser = new User();
        requestUser.setName("MegaOleg");
        requestUser.setAge(30);

        HttpEntity<User> entity = new HttpEntity<>(requestUser);
        ResponseEntity<User> responseEntity = restTemplate.exchange(getUri(String.valueOf(user.getId())),
                                                                    HttpMethod.PUT, entity, User.class);
        User responseUser = responseEntity.getBody();

        assertNotNull(responseEntity);
        assertNotNull(responseUser);
        assertNotNull(responseUser.getName());
        assertEquals(responseUser.getName(), requestUser.getName());
        assertEquals(responseUser.getAge(), requestUser.getAge());
    }

    @Test
    void deleteUser() throws URISyntaxException {
        Long id = user.getId();
        restTemplate.delete(getUri(String.valueOf(user.getId())));
        boolean isExist = repository.findById(id).isPresent();

        assertFalse(isExist);
    }


    private URI getUri(String urlEnd) throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/user/" + urlEnd;
        return new URI(baseUrl);
    }

    private URI getUri() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/user/";
        return new URI(baseUrl);
    }
}
