package com.example.demo.rest;

import com.example.demo.model.ItemDto;
import com.example.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("rest/item/v1")
@CrossOrigin(origins = "*")
@Slf4j
public class ItemController {

    DemoService demoService;

    ItemController(DemoService demoService) {
        this.demoService = demoService;
    }

    private int timer = 0;
    private int error = 0;

    @GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
    public String random(int random) {
        try {
            Thread.sleep(random);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            log.debug(e.getMessage(), e);
            Thread.currentThread().interrupt();
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "hello world random";
    }

    @PostMapping(value = "/items", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postItems(@RequestBody ItemDto itemDto) {
        demoService.createItem(itemDto);
    }

    @PostMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    public void error(Integer init) {
        if (init != null) {
            error = init;
        } else {
            if (error > 50) {
                throw new NullPointerException();
            }
            error++;
        }
    }

    @GetMapping(value = "/slower", produces = MediaType.APPLICATION_JSON_VALUE)
    public String slower() {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            log.debug(e.getMessage(), e);
            Thread.currentThread().interrupt();
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        timer += 1000;
        return "hello world slower";
    }

    @GetMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDto> getItems() {
        return demoService.getItems();
    }


}
