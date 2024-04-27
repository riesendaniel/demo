package com.example.demo.rest;

import com.example.demo.model.ItemDto;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/item/v1")
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    DemoService demoService;

    private int timer = 0;
    private int error = 0;

    @GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
    public String random(int random) {
        try {
            Thread.sleep(random);
        } catch (InterruptedException e) {
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
        }
        timer += 1000;
        return "hello world slower";
    }

    @GetMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDto> getItems() {
        return demoService.getItems();
    }


}
