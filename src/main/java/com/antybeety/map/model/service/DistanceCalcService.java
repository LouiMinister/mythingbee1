package com.antybeety.map.model.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DistanceCalcService {

    // 직선 거리 구하는 메서드
    public int calcDistance(double startLat, double startLon, double endLat, double endLon){
        double theta = startLon - endLon;
        double dist = Math.sin(deg2rad(startLat)) * Math.sin(deg2rad(endLat)) + Math.cos(deg2rad(startLat)) * Math.cos(deg2rad(endLat)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = red2deg(dist);
        dist = dist * 60 * 1.1515 * 1609.344;
        return (int) (dist);
    }

    // convert decimal degrees to radians
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // convert radians to decimal degrees
    private double red2deg(double rad){
        return (rad * 180.0 / Math.PI);
    }

    // 두 지점 사이의 각도 구하는 메서드
    private double calcDegree(double startLat, double startLon, double endLat, double endLon){
        double angle = red2deg(Math.atan2(startLat - endLat, startLon - endLon));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

    // 두 지점 사이의 예측 거리 구하는 메서드 ( 가로 더하기 세로 )
    public int predictDistance(double startLat, double startLon, double endLat, double endLon) {
        // 직선 거리구하고
        int straightDistance = calcDistance(startLat, startLon, endLat, endLon);
        // x축을 기준으로 두 지점 사이의 각도 구하고
        double degree = calcDegree(startLat, startLon, endLat, endLon);

        // 가로 세로 길이 구해서 대충 예상 거리를 구한다.
        return (int) (straightDistance * Math.cos(degree) + straightDistance * Math.sin(degree));
    }

    // 두 지점을 대각선에 두고 사각형을 만들었을 때 사각형의 넓이를 구하는 메서드
    public double calcArea(double startLat, double startLon, double endLat, double endLon){

        int straightDistance = calcDistance(startLat, startLon, endLat, endLon);

        double degree = calcDegree(startLat, startLon, endLat, endLon);

        // 가로 곱하기 세로 리턴
        return straightDistance * Math.cos(degree) * straightDistance * Math.sin(degree);
    }

    // map으로 왔을 때
    public double calcArea(Map<String,Object> bounds){
        double startLat =(double) bounds.get("la");
        double startLon =(double) bounds.get("ea");
        double endLat = (double) bounds.get("ka");
        double endLon = (double) bounds.get("ja");

        return this.calcArea(startLat,startLon,endLat,endLon);
    }

    public List<Integer> calcDistanceAll(List<Map<String,Object>> locationList){
        List<Integer> result = new ArrayList<>();

        double startLat = (double) locationList.get(0).get("lat");
        double startLon = (double) locationList.get(0).get("lon");

        for(int i=1; i<locationList.size(); i++){
            int distance =calcDistance(startLat,startLon,(double) locationList.get(i).get("lat"), (double)locationList.get(i).get("lon"));
            result.add(distance);
        }
        return result;
    }
}
