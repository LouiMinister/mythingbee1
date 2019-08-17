package com.antybeety.news.model.vo;

import lombok.Data;

@Data
public class KeywordVO {
    private String name;
    private String code;

    public KeywordVO(String name, String code){
        this.name=name;
        this.code=code;
    }

    public KeywordVO(){

    }

    @Override
    public String toString() {
        return "KeywordVO{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
