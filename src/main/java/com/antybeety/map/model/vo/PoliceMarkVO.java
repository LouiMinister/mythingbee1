package com.antybeety.map.model.vo;

import lombok.Data;

@Data
public class PoliceMarkVO implements  FacilityMarkVO{
    private String code;
    private double lat;
    private double lng;
}
