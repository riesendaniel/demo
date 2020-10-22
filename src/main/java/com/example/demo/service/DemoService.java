package com.example.demo.service;

import com.example.demo.jpa.ItemRepository;
import com.example.demo.model.Item;
import com.example.demo.model.ItemDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DemoService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private ItemRepository repository;

    public List<ItemDto> getItems() {
        return convertToDTO(repository.findAllBy());
    }

    private List<ItemDto> convertToDTO(List<Item> data) {
        return data.stream().map(d -> modelMapper.map(d, ItemDto.class)).collect(Collectors.toList());
    }
}
