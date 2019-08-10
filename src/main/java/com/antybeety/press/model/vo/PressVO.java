package com.antybeety.press.model.vo;

import lombok.Data;
@Data
public class PressVO {
    private String code;
    private String name;

    public PressVO(){}

    public PressVO(String code, String name){
        this.name  = name;
        this.code = code;
    }
}
