package com.erp.admin.repository;

import com.erp.admin.AdminApplicationTests;
import com.erp.admin.model.entity.Item;
import com.erp.admin.model.entity.OrderDetail;
import com.erp.admin.model.entity.User;
import junit.framework.TestCase;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class OrderDetailRepositoryTest extends AdminApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderAt(LocalDateTime.now());

        User user = new User();
        user.setId(1L);
        // 주문자 설정
        orderDetail.setUser(user);

        Item item = new Item();
        item.setId(1L);
        // 살품 설정
        orderDetail.setItem(item);

        // 데이터 확인
        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(newOrderDetail);
    }
}