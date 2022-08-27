package com.buyfresh.service.controller;

import com.buyfresh.service.DTO.ItemDTO;
import com.buyfresh.service.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/item")
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{itemId}")
    public ItemDTO getItem(String itemId) throws Exception {
        return itemService.getItem(itemId);
    }

    @PostMapping
    public ItemDTO createItem(ItemDTO itemDTO) {
        return itemService.createItem(itemDTO);
    }


}
