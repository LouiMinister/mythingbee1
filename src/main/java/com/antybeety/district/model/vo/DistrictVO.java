package com.antybeety.district.model.vo;

import lombok.Data;

@Data
public class DistrictVO {
    private String code;
    private String name;

    public DistrictVO(){}

    public DistrictVO(String code, String name){
        this.code = code;
        this.name = name;
    }
}
