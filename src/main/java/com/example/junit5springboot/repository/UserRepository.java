package com.example.junit5springboot.repository;

import com.example.junit5springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Oleg Pavlyukov
 * on 30.12.2019
 * cpabox777@gmail.com
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByName(String name);
    boolean deleteUserById(Long id);
}
