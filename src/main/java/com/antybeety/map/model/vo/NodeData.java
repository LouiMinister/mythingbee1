package com.antybeety.map.model.vo;

import lombok.Data;

import java.util.Map;

public class NodeData {
    private final Integer nodeId; //  노드 아이디
    private final Map<Integer, Double> heuristic; // 다른 노드까지의 예상 비용 Map
    private double lat; // 위도
    private double lon; // 경도

    private double g;  // g is distance from the source // 출발지점부터 현재 노드까지의 비용
    private double h;  // h is the heuristic of destination.    // 목적지까지의 추정 비용
    private double f;  // f = g + h // 출발지부터의 비용과 목적지까지의 추정 비용의 합

    public NodeData(Integer nodeId, Map<Integer, Double> heuristic) {
        this(nodeId, heuristic, 0, 0);
    }

    public NodeData(Integer nodeId,Map<Integer, Double> heuristic, double lat, double lon){
        this.nodeId = nodeId;
        this.heuristic = heuristic;
        this.g = Double.MAX_VALUE;
        this.lat = lat;
        this.lon = lon;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    // destination 까지의 예상 비용을 구한 뒤 f값을 구한다
    public void calcF(Integer destination) {
        this.h = heuristic.get(destination);
        this.f = g + h;

    }

    public double getH() {
        return h;
    }

    public double getF() {
        return f;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
