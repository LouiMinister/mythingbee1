package com.antybeety.stats.model.vo;

import lombok.Data;

@Data
public class CrimeStatsVO {
    private String name;
    private String district;
    private int year;
    private int occurCnt;
    private int arrestCnt;
    private int population;

    public CrimeStatsVO() {}
    public CrimeStatsVO(String name, String district, int year, int occurCnt, int arrestCnt, int population) {
        this.name = name;
        this.district = district;
        this.year = year;
        this.occurCnt = occurCnt;
        this.arrestCnt = arrestCnt;
        this.population = population;
    }
    @Override
    public String toString() {
        return "CrimeStatsVO [name=" + name + ", district=" + district + ", year=" + year + ", occurCnt=" + occurCnt
                + ", arrestCnt=" + arrestCnt + ", population=" + population + "]";
    }
}
