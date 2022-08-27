package com.buyfresh.service.repository;

import com.buyfresh.service.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository
        extends MongoRepository<Item, String> {
}
