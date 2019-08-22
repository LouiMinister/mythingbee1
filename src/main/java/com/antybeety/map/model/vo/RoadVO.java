package com.antybeety.map.model.vo;

import lombok.Data;

@Data
public class RoadVO {
    private int roadIndex;
    private String roadName;
    private int roadCode;
    private String startPoint;
    private String endPoint;
    private int roadWidth;
    private int roadLength;
    private double lat;
    private double lon;
}
