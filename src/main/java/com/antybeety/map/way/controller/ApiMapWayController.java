package com.antybeety.map.way.controller;

import com.antybeety.map.controller.FacilityController;
import com.antybeety.map.model.vo.FacilityDetailVO;
import com.antybeety.map.model.vo.FacilityMarkVO;
import com.antybeety.map.mybatis.MapMapper;
import com.antybeety.map.way.model.dao.EdgeDAO;
import com.antybeety.map.way.model.dao.NodeDAO;
import com.antybeety.map.way.model.dao.OsmDAO;
import com.antybeety.map.way.model.service.DistanceCalcService;
import com.antybeety.map.way.model.service.MapSettingService;
import com.antybeety.map.way.model.vo.EdgeVO;
import com.antybeety.map.way.model.vo.NodeData;
import com.antybeety.map.way.model.vo.NodeVO;
import com.antybeety.map.way.mybatis.MapWayMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

@RestController
@RequestMapping("/api/map/way")
public class ApiMapWayController {

    public static final double MAX_TOP = 37.498184268458694;
    public static final double MAX_BOTTOM = 37.4680270499956;
    public static final double MAX_RIGHT = 126.9760561098761;
    public static final double MAX_LEFT = 126.92032849888486;


    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private EdgeDAO edgeDAO;

    @Autowired
    private NodeDAO nodeDAO;

    @Autowired
    private OsmDAO osmDAO;

    @Autowired
    private DistanceCalcService distanceCalcService;

    @Autowired
    private MapSettingController mapSettingController;

    @Autowired
    private FindPathController findPathController;

    @Autowired
    private MapSettingService mapSettingService;

    @RequestMapping(value="/safe", method = RequestMethod.GET)
    public List<NodeVO> searchSafePath(@RequestParam double startLat, double startLon, double endLat, double endLon){
        // 영역 제한 걸기
        if(startLat > MAX_TOP || startLat < MAX_BOTTOM) return null;
        if(endLat > MAX_TOP ||  endLat < MAX_BOTTOM) return null;
        if(startLon > MAX_RIGHT || startLon < MAX_LEFT) return null;
        if(endLon > MAX_RIGHT || endLon < MAX_LEFT) return null;

        return findPathController.searchSafePath(startLat, startLon, endLat, endLon);
    }

    @RequestMapping(value = "/heuristic", method = RequestMethod.GET)
    public void setHeuristic(){
        mapSettingController.setHeuristic();
    }

    @RequestMapping(value="/node", method = RequestMethod.GET)
    public List<NodeVO> getAllNode(){

        List<NodeVO> result = nodeDAO.getAllNode();

        return result;
    }

    @RequestMapping(value="/edge", method = RequestMethod.GET)
    public List<Map<String,Object>> getAllEdge(){

        List<EdgeVO> edges = edgeDAO.getAllEdge();

        List<NodeVO> nodes = nodeDAO.getAllNode();

        List<Map<String,Object>> result = new ArrayList<>();

        Map<String,Object> data;

        for(EdgeVO e : edges){
            data = new HashMap<>();
            data.put("edgeId",e.getId());
            for(NodeVO n : nodes){
                if(n.getId() == (e.getNodeStart())){
                    data.put("startLat",n.getLat());
                    data.put("startLon",n.getLng());
                } else if(n.getId() == ( e.getNodeEnd())){
                    data.put("endLat",n.getLat());
                    data.put("endLon",n.getLng());
                }
            }
            result.add(data);
        }
        return result;
    }

    /**
     * osm 파일을 통해 노드, 엣지를 세팅하는 메서드
     */
    @RequestMapping(value = "/settingLineByOsm", method = RequestMethod.GET)
    public void settingLineByOsm(){
        System.out.println("settingLineByOsm");
        MapWayMapper mapMapper = sqlSession.getMapper(MapWayMapper.class);

        Queue<ConcurrentLinkedQueue<Double[]>> osmLines = osmDAO.getOsmLines();

        for(ConcurrentLinkedQueue<Double[]> osmLine : osmLines){
            for(Double[] osmPoint: osmLine){
                Map<String,Object> node = new HashMap<>();
                node.put("lat",osmPoint[1]);
                node.put("lon",osmPoint[0]);

                mapMapper.addNode(node);
            }
        }
    }

    @RequestMapping(value = "/addNode", method = RequestMethod.GET)
    public void addNode(@RequestParam Long index, double lat, double lon){

        Map<String,Object> node = new HashMap<>();

        node.put("index",index);
        node.put("lat",lat);
        node.put("lon",lon);

        MapWayMapper mapMapper = sqlSession.getMapper(MapWayMapper.class);
        mapMapper.addNode(node);
    }

    @RequestMapping(value="/deleteNode", method = RequestMethod.GET)
    public void deleteNode(@RequestParam long index){
        MapWayMapper mapMapper = sqlSession.getMapper(MapWayMapper.class);
        mapMapper.deleteNode(index);
    }

    @RequestMapping(value="/addEdge", method = RequestMethod.GET)
    public void addEdge(@RequestParam long index, Long startNode, double startLat, double startLon,
                        Long endNode, double endLat, double endLon){

        Map<String, Object> edge = new HashMap<>();

        edge.put("index",index);
        edge.put("startNode",startNode);
        edge.put("endNode",endNode);

        int distance = distanceCalcService.calcDistance(startLat, startLon, endLat, endLon);

        edge.put("distance",distance);

        MapWayMapper mapMapper = sqlSession.getMapper(MapWayMapper.class);
        mapMapper.addEdge(edge);
    }

    @RequestMapping(value="/deleteEdge", method = RequestMethod.GET)
    public void deleteEdge(@RequestParam Long index){
        MapWayMapper mapMapper = sqlSession.getMapper(MapWayMapper.class);
        mapMapper.deleteEdge(index);
    }

    @RequestMapping(value="/setting", method = RequestMethod.GET)
    public void setMap(){
        mapSettingController.setAllSafetyValue();
    }

    @RequestMapping(value="/updateEdgeInfo", method = RequestMethod.GET)
    public void updateEdgeInfo(@RequestParam long index,
                               @RequestParam(required = false, defaultValue = "0") int landType,
                               @RequestParam(required = false, defaultValue = "0") int safetyScore,
                               @RequestParam(required = false, defaultValue = "0") int roadType){

        System.out.println("updateEdgeInfo");
        System.out.println(index +" "+ landType+ " "+ safetyScore+" "+roadType);
        mapSettingService.addRoadInfo(index, landType, roadType, safetyScore);
    }
}