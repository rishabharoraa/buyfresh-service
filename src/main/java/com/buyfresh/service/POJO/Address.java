package com.buyfresh.service.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private String line1;
    private String line2;
    private String line3;

    private String City;
    private String State;
    private String Country;

    private String postalCode;
}
