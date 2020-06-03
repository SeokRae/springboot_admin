package com.erp.admin.repository;

import com.erp.admin.AdminApplicationTests;
import com.erp.admin.model.entity.Category;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends AdminApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {
        String type = "computer";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);
        Assert.assertNotNull(newCategory);
        Assert.assertEquals(newCategory.getTitle(), title);
        Assert.assertEquals(newCategory.getType(), type);
    }

    @Test
    public void read() {
        Optional<Category> optionalCategory = categoryRepository.findById(1L);
        Assert.assertNotNull(optionalCategory.isPresent());

        optionalCategory.ifPresent(c -> {
            System.out.println(c.getTitle());
        });
    }

    @Test
    public void readWithType() {
        String type = "computer";
        Optional<Category> optionalCategory = categoryRepository.findByType("computer");
        Assert.assertNotNull(optionalCategory.isPresent());

        optionalCategory.ifPresent(c -> {
            Assert.assertEquals(c.getType(), type);
            System.out.println(c.getTitle());
            System.out.println(c.getType());
        });
    }
}
