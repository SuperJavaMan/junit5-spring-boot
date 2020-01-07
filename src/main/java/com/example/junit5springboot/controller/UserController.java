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
        User addUser = new User();
        addUser.setName(user.getName());
        addUser.setAge(user.getAge());
        return repository.save(addUser);
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
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().body("User deleted successfully!");
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("User deletion error! -> " + ex.getMessage());
        }
    }
}
