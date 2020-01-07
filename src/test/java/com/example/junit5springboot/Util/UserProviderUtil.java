package com.example.junit5springboot.Util;

import com.example.junit5springboot.model.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Oleg Pavlyukov
 * on 06.01.2020
 * cpabox777@gmail.com
 */
public class UserProviderUtil {

    public static List<User> generateUserList(int length) {
        return Stream
                .iterate(1, n -> n + 1).limit(length)
                .map(num -> {
                    User user = new User();
                    user.setName("Test User " + num);
                    user.setAge(num + 20);
                    return user;
                })
                .collect(Collectors.toList());
    }

    public static User generateUser(Long id, String name, int age) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    }
}
