package com.erp.admin.repository;

import com.erp.admin.AdminApplicationTests;
import com.erp.admin.model.entity.Item;
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
        User user = new User();
        user.setAccount("TestUser01");
        user.setEmail("test01@gmail.com");
        user.setPhoneNumber("010-1111-1111");
        user.setCreatedBy("admin");
        user.setCreatedAt(LocalDateTime.now());

        User newUser = userRepository.save(user);
        System.out.println(newUser);
    }

    @Test
    @Transactional
    public void read() {
        Optional<User> user = userRepository.findById(1L);
        user.ifPresent(selectedUser -> {

            selectedUser.getOrderDetailList().stream().forEach(detail -> {
                Item item = detail.getItem();
                System.out.println(item);
            });

        });
    }

    @Test
    @Transactional
    public void readAtAccount() {
        Optional<User> user = userRepository.findByAccount("TestUser01");
        user.ifPresent(selectedUser -> {

            selectedUser.getOrderDetailList().stream().forEach(detail -> {
                Item item = detail.getItem();
                System.out.println(item);
            });

        });
    }

    @Test
    @Transactional
    public void readAtEmail() {
        Optional<User> user = userRepository.findByEmail("test01@gmail.com");
        user.ifPresent(selectedUser -> {

            selectedUser.getOrderDetailList().stream().forEach(detail -> {
                Item item = detail.getItem();
                System.out.println(item);
            });
        });
    }

    @Test
    @Transactional
    public void readAtAccountAndEmail() {
        Optional<User> user = userRepository.findByAccountAndEmail("TestUser01", "test01@gmail.com");
        user.ifPresent(selectedUser -> {

            selectedUser.getOrderDetailList().stream().forEach(detail -> {
                Item item = detail.getItem();
                System.out.println(item);
            });
        });
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
