package com.erp.admin.repository;

import com.erp.admin.AdminApplicationTests;
import com.erp.admin.model.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class UserRepositoryTest extends AdminApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        User user = new User();
        user.setAccount("TestUser01");
        user.setPassword("1234");
        user.setStatus("Good");
        user.setEmail("test01@gmail.com");
        user.setPhoneNumber("010-1111-1111");
        user.setCreatedBy("admin");
        user.setCreatedAt(LocalDateTime.now());

        User newUser = userRepository.save(user);
        System.out.println(newUser);
    }
}
