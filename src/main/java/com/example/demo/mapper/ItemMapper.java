package com.example.demo.mapper;

import com.example.demo.model.Item;
import com.example.demo.model.ItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto mapToDto(Item employee);

    Item mapToSource(ItemDto employeeDto);

}
