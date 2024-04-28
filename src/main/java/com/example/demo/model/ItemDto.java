package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDto {

    private Long id;
    private String name;

    public ItemDto() {
        // default constructor
    }

}
