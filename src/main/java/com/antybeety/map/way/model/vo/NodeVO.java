package com.antybeety.map.way.model.vo;

import lombok.Data;

@Data
public class NodeVO {
    private long id;
    private double lat;
    private double lng;

    public NodeVO(){}

    public NodeVO(long id,double lat, double lng) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
    }
}

