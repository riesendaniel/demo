package com.example.demo.service;

import com.example.demo.jpa.ItemRepository;
import com.example.demo.mapper.ItemMapper;
import com.example.demo.model.Item;
import com.example.demo.model.ItemDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DemoService {


    final ItemRepository repository;

    final ItemMapper itemMapper;

    DemoService(ItemRepository repository, ItemMapper itemMapper) {
        this.repository = repository;
        this.itemMapper = itemMapper;
    }

    public List<ItemDto> getItems() {
        return convertToDTO(repository.findAllBy());
    }

    private List<ItemDto> convertToDTO(List<Item> data) {
        return data.stream().map(itemMapper::mapToDto).toList();
    }

    public void createItem(ItemDto itemDto) {
        repository.save(convertToEntity(itemDto));
    }

    private Item convertToEntity(ItemDto itemDto) {
        return itemMapper.mapToSource(itemDto);
    }
}
