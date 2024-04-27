package com.example.demo.service;

import com.example.demo.jpa.ItemRepository;
import com.example.demo.mapper.ItemMapper;
import com.example.demo.model.Item;
import com.example.demo.model.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DemoService {

    @Autowired
    private ItemRepository repository;
    @Autowired
    ItemMapper itemMapper;

    public List<ItemDto> getItems() {
        return convertToDTO(repository.findAllBy());
    }

    private List<ItemDto> convertToDTO(List<Item> data) {
        return data.stream().map(d -> itemMapper.mapToDto(d)).collect(Collectors.toList());
    }

    public void createItem(ItemDto itemDto) {
        repository.save(convertToEntity(itemDto));
    }

    private Item convertToEntity(ItemDto itemDto) {
        return itemMapper.mapToSource(itemDto);
    }
}
