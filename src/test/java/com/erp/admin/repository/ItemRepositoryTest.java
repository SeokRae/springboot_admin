package com.erp.admin.repository;

import com.erp.admin.AdminApplicationTests;
import com.erp.admin.model.entity.Item;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;

public class ItemRepositoryTest extends AdminApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();

        Item item = new Item();
        item.setTitle("삼성 노트북 8");
        item.setName("노트북");
        item.setContent("델 노트북");
        item.setPrice(900000);
        item.setBrandName("삼성");
        item.setStatus("unregistered");
        item.setRegisteredAt(registeredAt);
        item.setCreatedAt(createdAt);
        item.setCreatedBy("partner01");
//        item.setPartnerId(1L);

        Item newItem = itemRepository.save(item);
        Assert.assertNotNull(newItem);
    }

    @Test
    public void read() {
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);
        Assert.assertTrue(item.isPresent());

    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {

    }
}