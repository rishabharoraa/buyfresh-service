package com.buyfresh.service.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShelfLife {
    private Integer hours;
    private Integer days;
    private Integer months;
    private Integer years;
}
