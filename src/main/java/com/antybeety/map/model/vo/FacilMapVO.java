package com.antybeety.map.model.vo;

import lombok.Data;

@Data
public class FacilMapVO {

    private String bounds;
    private String facilFlag;
    private String facilName;

    public FacilMapVO() {

    }

    public FacilMapVO(String bounds, String facilFlag, String facilName) {
        this.bounds = bounds;
        this.facilFlag = facilFlag;
        this.facilName = facilName;
    }
}
