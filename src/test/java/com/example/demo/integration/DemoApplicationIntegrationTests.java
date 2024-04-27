package com.example.demo.integration;

import com.example.demo.model.ItemDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.env.Environment;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Test
    void testDemoControllerItems() {
        String url = "http://localhost:" + port + "/rest/item/v1/items";
        ItemDto[] data = this.restTemplate.withBasicAuth("admin", "admin").getForObject(url, ItemDto[].class);
        Assertions.assertNotNull(data);
        Assertions.assertEquals(2, data.length);
    }
}
