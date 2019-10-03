package com.antybeety.map.way.model.service;

import com.antybeety.map.model.dao.FacilityDetailDAO;
import com.antybeety.map.model.dao.FacilityDetailDAOImpl;
import com.antybeety.map.model.service.FacilityDisplayService;
import com.antybeety.map.model.vo.FacilityMarkVO;
import com.antybeety.map.way.model.dao.*;
import com.antybeety.map.way.model.vo.EdgeVO;
import com.antybeety.map.way.model.vo.NodeVO;
import com.antybeety.map.way.model.vo.RoadVO;
import com.sun.org.apache.xerces.internal.impl.dv.xs.EntityDV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MapSettingService {

    @Autowired
    private FacilityDisplayService fDisplay;

    @Autowired
    private List<FacilityDetailDAOImpl> detailDAO;

    @Autowired
    private DistanceCalcService distanceCalc;

    @Autowired
    private EdgeDAO edgeDAO;

    @Autowired
    private NodeDAO nodeDAO;

    @Autowired
    private RoadDAO roadDAO;

    @Autowired
    private HeuristicDAO heuristicDAO;

    @Autowired
    private AiDataDAO aiDataDAO;

    // 두 노드 사이에 안전 가중치 세팅하는 메서드
    public void setHeuristicValue() {

        List<NodeVO> nodeList = nodeDAO.getAllNode();

        // 노드 뽑은 뒤 영역 구하기
        Map<String, Object> bounds = new HashMap<>();
        double left;
        double right;
        double bottom;
        double top;

        NodeVO node_i;
        NodeVO node_j;

        int safetyValueSum = 0;

        for (int i = 0; i < nodeList.size() - 1; i++) {
            node_i = nodeList.get(i);
            for (int j = i + 1; j < nodeList.size(); j++) {
                node_j = nodeList.get(j);
                // 위 아래 구하기
                if (node_i.getLat() > node_j.getLat()) {
                    bottom = node_j.getLat();
                    top = node_i.getLat();
                } else {
                    bottom = node_i.getLat();
                    top = node_j.getLat();
                }

                // 좌우 구하기
                if (node_i.getLng() > node_j.getLng()) {
                    right = node_i.getLng();
                    left = node_j.getLng();
                } else {
                    right = node_j.getLng();
                    left = node_i.getLng();
                }
                bounds.put("la", left);
                bounds.put("ka", right);
                bounds.put("ea", bottom);
                bounds.put("ja", top);

                // 영역 내의 모든 시설물 가져오기
                List<FacilityMarkVO> allFacility = fDisplay.searchAroundFacilities(bounds);

                safetyValueSum = 0;
                // 영역 내의 총 안전수치 점수
                for (FacilityMarkVO v : allFacility) {
                    switch (v.getCode().substring(0, 2)) {
                        case "CC":
                            safetyValueSum += 8;
                            break;
                        case "LI":
                            safetyValueSum += 3;
                            break;
                        case "PD":
                            safetyValueSum += 50;
                            break;
                        case "CS":
                            safetyValueSum += 20;
                            break;
                        case "BE":
                            safetyValueSum++;
                            break;
                    }
                }
                double heuristic = safetyValueSum / distanceCalc.calcArea(bounds);

                if (node_i.getId() > node_j.getId()) {
                    heuristicDAO.setHeuristic(node_j.getId(), node_i.getId(), heuristic);
                } else {
                    heuristicDAO.setHeuristic(node_i.getId(), node_j.getId(), heuristic);
                }
            }
        }


    }

    // 경로에 안전 가중치 부여하기
    public void setAllSafetyValue() {
        List<EdgeVO> edgeList = edgeDAO.getAllEdge();

        List<String> roadAddrList = null;
        int point = 0;
        // 시설물 dao 반복
        for (FacilityDetailDAOImpl d : detailDAO) {
            switch (d.getFacilName()) {
                case "CC":
                    point = 1;//포인트제가 아니라 테이블에 추가해야함
                    break;
                case "BE":
                    point = 1;
                    break;
                case "PD":
                    point = 1;
                    break;
                case "LI":
                    point = 1;
                    break;
                case "CS":
                    point = 1;
                    break;
                default:
                    break;
            }

            roadAddrList = d.searchAllRoadAddr();    // 시설물들의 도로명 주소 리스트

            String facilRoadAddr_p = null;
            String edgeRoadAddr_p = null;
            for (String roadAddr : roadAddrList) {
                if (roadAddr == null) continue;
                facilRoadAddr_p = parseRoadAddr(roadAddr);
                for (EdgeVO e : edgeList) {   // 간선 리스트
                    if (e.getAddress() == null) continue;
                    edgeRoadAddr_p = parseRoadAddr(e.getAddress());

                    if (facilRoadAddr_p.equals(edgeRoadAddr_p)) {
                        e.addSafeVal(point);
                    }
                }
            }
        }
        edgeDAO.setSafetyValue(edgeList);
    }

    // 도로명 주소 파싱하기
    private String parseRoadAddr(String roadAddr) {
        if (roadAddr.contains("길")) {
            int index = roadAddr.indexOf("길");
            return roadAddr.substring(0, index + 1);
        } else if (roadAddr.contains("로")) {
            int index = roadAddr.indexOf("로");
            return roadAddr.substring(0, index + 1);
        } else {
            return roadAddr;
        }
    }

    // 도로명 주소에 전체 길이 추가하기 // 도로명 코드 필요함
    private void setAllRoadDistance() {
        List<RoadVO> roadList = roadDAO.searchAllRoad();
        List<EdgeVO> edgetList = edgeDAO.getAllEdge();

        // 모든 간선 하나씩
        for (EdgeVO e : edgetList) {
            // 모든 도로명 하나씩
            for (RoadVO r : roadList) {
                // 간선의 도로명 주소가 도로의 주소명을 포함하고 있으면
                if (e.getAddress().contains(r.getName())) {

                }
            }
        }
    }

    // 모든 경로의 도로명 주소 구하기    // 안 쓰임
    public void setAllRoadAddr() {
        List<EdgeVO> edgeList = edgeDAO.getAllEdge();
        List<NodeVO> nodeList = nodeDAO.getAllNode();

        Map<String, Object> locationList = new HashMap<>();

        double startLat = 0;
        double startLon = 0;
        double endLat = 0;
        double endLon = 0;

        for (EdgeVO e : edgeList) {
            locationList.put("id", e.getId());
            for (NodeVO n : nodeList) {
                if (e.getNodeStart() == n.getId()) {
                    startLat = n.getLat();
                    startLon = n.getLng();
                } else if (e.getNodeEnd() == n.getId()) {
                    endLat = n.getLat();
                    endLon = n.getLng();
                }
            }

            locationList.put("lat", (startLat + endLat) / 2);
            locationList.put("lon", (startLon + endLon) / 2);

            edgeDAO.setLocation(locationList);
        }
    }

    // 일단 패스 // 안 쓰임
    public void setAllDistance() {
        List<EdgeVO> edgeList = edgeDAO.getAllEdge();

    }

    public void addRoadInfo(double edgeId, int landType, int roadType, int safeRate) {
        aiDataDAO.addRoadInfo(edgeId, landType, roadType, safeRate);
    }
}
