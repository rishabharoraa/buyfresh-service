package com.buyfresh.service.service;

import com.buyfresh.service.DTO.ItemDTO;
import com.buyfresh.service.model.Item;
import com.buyfresh.service.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public List<ItemDTO> getAllItems() {
        return itemRepository
                .findAll()
                .stream()
                .map(
                        ItemDTO::fromItem
                )
                .toList();
    }

    public ItemDTO getItem(String id) throws Exception {
        Item itemToReturn = itemRepository
                .findById(id)
                .orElseThrow(
                        () -> new Exception("Item not found")
                );
        return ItemDTO.fromItem(itemToReturn);
    }

    public ItemDTO createItem(@NotNull ItemDTO itemDTO) {
        Item itemToSave = itemRepository.save(itemDTO.toItem());
//        Item itemToSave = new Item(100L, 100L, "tset", null)
        return ItemDTO.fromItem(itemToSave);
    }

}
