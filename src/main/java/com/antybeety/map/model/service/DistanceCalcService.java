package com.antybeety.map.model.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DistanceCalcService {
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
