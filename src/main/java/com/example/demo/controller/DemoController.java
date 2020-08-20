package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/demo/v1")
public class DemoController {

    @Autowired
    DemoRepository repository;

    private int timer = 0;

    @GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
    public String random(int random) {
        try {
            Thread.sleep(random);
        } catch (InterruptedException e) {
        }
        return "hello world random";
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

    @GetMapping(value = "/names", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getNames() {
        return repository.findAllBy().toString();
    }


}
