package com.antybeety.map.report.model.vo;

import lombok.Data;
@Data
public class ReportVO {
    private int id;
    private double lat;
    private double lon;
    private String address;
    private String date;
    private String comments;
    private String category;


    public ReportVO(){}
    public ReportVO(int id, double lat, double lon, String address,String category, String date, String comments)
    {
        this.id=id;
        this.lat=lat;
        this.lon=lon;
        this.address=address;
        this.category=category;
        this.date=date;
        this.comments=comments;

    }
    public ReportVO(double lat, double lon, String address,String category, String date, String comments) {
        this.lat=lat;
        this.lon=lon;
        this.address=address;
        this.category=category;
        this.date=date;
        this.comments=comments;
    }

    @Override
    public String toString() {
        return "ReportVO{" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                ", address='" + address + '\'' +
                ", date='" + date + '\'' +
                ", comments='" + comments + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
