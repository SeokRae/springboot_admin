package com.erp.admin.repository;

import com.erp.admin.AdminApplicationTests;
import com.erp.admin.model.entity.OrderGroup;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class OrderGroupRepositoryTest extends AdminApplicationTests {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void create() {
        OrderGroup orderGroup = new OrderGroup();
        orderGroup.setStatus("complete");
        orderGroup.setOrderType("All");
        orderGroup.setPaymentType("card");
        orderGroup.setRevAddress("Seoul");
        orderGroup.setRevName("익명");
        orderGroup.setTotalPrice(BigDecimal.valueOf(900000));
        orderGroup.setTotalQuantity(1);
        orderGroup.setOrderAt(LocalDateTime.now().minusDays(2));
        orderGroup.setArrivalDate(LocalDateTime.now());
        orderGroup.setCreatedAt(LocalDateTime.now());
        orderGroup.setCreatedBy("admin");

//        orderGroup.setUser(1L);

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        Assert.assertNotNull(newOrderGroup);

    }
}