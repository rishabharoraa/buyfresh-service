package com.buyfresh.service.model;

import com.buyfresh.service.POJO.NutritionalInformation;
import com.buyfresh.service.POJO.ShelfLife;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Item {
    @Id
    private String id;

    private String name;

    private Integer price;
    private Long stock;

    private String vendorId;

    private NutritionalInformation nutritionalInformation;
    private ShelfLife shelfLife;

    public Item(String name,
                Integer price,
                Long stock,
                String vendorId,
                NutritionalInformation nutritionalInformation,
                ShelfLife shelfLife) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.vendorId = vendorId;
        this.nutritionalInformation = nutritionalInformation;
        this.shelfLife = shelfLife;
    }
}
