package com.antybeety.map.way.model.vo;

import lombok.Data;

@Data
public class EdgeVO {
    private Long id;
    private Long nodeStart;
    private Long nodeEnd;
    private String address;
    private int safeVal;
    private int distanceVal;
}
