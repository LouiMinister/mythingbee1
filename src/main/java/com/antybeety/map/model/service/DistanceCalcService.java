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
        int startingDistance = calcDistance(startLat, startLon, endLat, endLon);
        // x축을 기준으로 두 지점 사이의 각도 구하고
        double degree = calcDegree(startLat, startLon, endLat, endLon);

        // 가로 세로 길이 구해서 대충 예상 거리를 구한다.
        return (int) (startingDistance * Math.cos(degree) + startingDistance * Math.sin(degree));
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
