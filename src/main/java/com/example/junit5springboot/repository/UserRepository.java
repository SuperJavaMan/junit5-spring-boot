package com.example.junit5springboot.repository;

import com.example.junit5springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Oleg Pavlyukov
 * on 04.01.2020
 * cpabox777@gmail.com
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByName(String name);
//    User findTopByAge();
    boolean existsByName(String name);
}
