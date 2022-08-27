package com.buyfresh.service.DTO;

import com.buyfresh.service.POJO.NutritionalInformation;
import com.buyfresh.service.POJO.ShelfLife;
import com.buyfresh.service.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
public class ItemDTO {
    private String name;

    private Integer price;
    private Long stock;

    private String vendorId;

    private NutritionalInformation nutritionalInformation;
    private ShelfLife shelfLife;

    public Item toItem() {
        return new Item(
                this.name,
                this.price,
                this.stock,
                this.vendorId,
                this.nutritionalInformation,
                this.shelfLife
        );
    }

    public static ItemDTO fromItem(@NotNull Item item) {
        return new ItemDTO(
                item.getName(),
                item.getPrice(),
                item.getStock(),
                item.getVendorId(),
                item.getNutritionalInformation(),
                item.getShelfLife()
        );
    }
}
