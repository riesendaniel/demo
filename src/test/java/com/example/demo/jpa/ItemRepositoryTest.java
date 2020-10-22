package com.example.demo.jpa;

import com.example.demo.model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql({"/schema-test.sql", "/data-test.sql"})
@ActiveProfiles("test")
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EntityManager entityManager;

    @AfterEach
    public void tearDown() {
        this.itemRepository.deleteAllInBatch();
    }

    @Test
    public void testFindAll() {
        List<Item> foundList = this.itemRepository.findAll();
        Assertions.assertNotNull(foundList);
        Assertions.assertEquals(foundList.size(), 3);
    }

}