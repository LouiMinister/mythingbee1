package com.antybeety.map.model.vo;

import lombok.Data;

@Data
public class ConvenienceMarkVO implements FacilityMarkVO{
    private String code;
    private double lat;
    private double lng;
}
