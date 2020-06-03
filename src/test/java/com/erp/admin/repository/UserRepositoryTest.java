package com.erp.admin.repository;

import com.erp.admin.AdminApplicationTests;
import com.erp.admin.model.entity.User;
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


        User user = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .phoneNumber(phone)
                .registeredAt(registeredAt)
                .build();

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
        user.getOrderGroupList().forEach(orderGroup -> {
            System.out.println("주문 정보");
            System.out.println(orderGroup.getRevAddress());
            System.out.println(orderGroup.getRevName());
            System.out.println(orderGroup.getTotalPrice());
            System.out.println(orderGroup.getTotalQuantity());

            orderGroup.getOrderDetailList().forEach(orderDetail -> {
                System.out.println("주문 상세 ");
                System.out.println(orderDetail.getItem().getPartner().getName());
                System.out.println(orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println(orderDetail.getItem().getName());
                System.out.println(orderDetail.getItem().getPartner().getCallCenter());
                System.out.println(orderDetail.getStatus());
                System.out.println(orderDetail.getArrivalDate());

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

        Assert.assertTrue(user.isPresent());

        user.ifPresent(selectedUser -> userRepository.delete(selectedUser));

        Optional<User> deletedUser = userRepository.findById(2L);
        Assert.assertFalse(deletedUser.isPresent());

    }


}
