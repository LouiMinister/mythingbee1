package com.antybeety.map.way.model.vo;

import lombok.Data;

@Data
public class NodeVO {
    private long id;
    private double lat;
    private double lng;

    public NodeVO(double v, double v1) {
        lat = v;
        lng = v1;
    }
}

