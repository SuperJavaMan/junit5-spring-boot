package com.example.junit5springboot.controller;

import com.example.junit5springboot.model.User;
import com.example.junit5springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Oleg Pavlyukov
 * on 04.01.2020
 * cpabox777@gmail.com
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody User userRequest) {
        User user = repository.findById(id).orElseThrow(NoSuchElementException::new);
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        return repository.save(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (repository.deleteUserById(id)) {
            return ResponseEntity.ok().body("User deleted successfully!");
        } else {
            return ResponseEntity.badRequest().body("User deletion error!");
        }
    }
}
