package com.erp.admin.repository;

import com.erp.admin.AdminApplicationTests;
import com.erp.admin.model.entity.User;
import org.apache.tomcat.jni.Local;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UserRepositoryTest extends AdminApplicationTests {

    @Autowired
    private UserRepository userRepository;

    /**
     * hibernate 특징 필드 매핑 하지 않고 오브젝트로 자동으로 매핑 해줌
     */
    @Test
    public void create() {
        String account = "Test01";
        String password = "Test01";
        String status = "Registered";
        String email = "Test@gmail.com";
        String phone = "010-1111-1111";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "Admin";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phone);
        user.setRegisteredAt(registeredAt);
        user.setCreatedBy(createdBy);
        user.setCreatedAt(LocalDateTime.now());

        User newUser = userRepository.save(user);
        Assert.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read() {
        Optional<User> user = userRepository.findById(1L);
        System.out.println(user);
    }

    @Test
    @Transactional
    public void readFindFirstByPhoneNumberOrderByIdDesc() {
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-1111");
        Assert.assertNotNull(user);
    }

    @Test
    public void readAll() {
        List<User> users = userRepository.findAll();
        System.out.println(users.size());
    }

    /**
     * update 로직도 우선 findById를 통해 데이터를 가져온다.
     */
    @Test
    @Transactional
    public void update() {
        Optional<User> user = userRepository.findById(1L);
        user.ifPresent(selectedUser -> {
            selectedUser.setAccount("ppp");
            selectedUser.setUpdatedBy("updateUser");
            selectedUser.setUpdatedAt(LocalDateTime.now());
            userRepository.save(selectedUser);
        });
    }

    @Test
    @Transactional
    public void delete() {
        Optional<User> user = userRepository.findById(1L);

        Assert.assertTrue(user.isPresent());;

        user.ifPresent(selectedUser -> {
            userRepository.delete(selectedUser);
        });

        Optional<User> deletedUser = userRepository.findById(2L);
        Assert.assertFalse(deletedUser.isPresent());

    }


}
