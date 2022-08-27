package com.buyfresh.service.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NutritionalInformation {

    private Float servingSize;

    private Float protein;
    private CarbohydrateContent carbs;
    private FatContent fat;
    private Float cholesterol;
    private Float calories;

    private Micronutrients micronutrients;


}
