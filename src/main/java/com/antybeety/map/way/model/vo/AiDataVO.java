package com.antybeety.map.way.model.vo;

import lombok.Data;

@Data
public class AiDataVO {
    private int edgeId;
    private String addr;
    private float light;
    private int cctv;
    private int police;
    private int convenience;
    private int landUse;
    private int road;
    private float safetyRate;
}
