package com.antybeety.map.way.mybatis;

import com.antybeety.map.way.model.vo.EdgeVO;
import com.antybeety.map.way.model.vo.NodeData;
import com.antybeety.map.way.model.vo.NodeVO;
import com.antybeety.map.way.model.vo.RoadVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MapWayMapper {
    List<NodeVO> getAllNode();

    List<EdgeVO> getAllEdge();

    List<NodeVO> searchNodeByArea(HashMap<String, Object> AreaByTwoPoints);
    List<EdgeVO> searchEdgesByArea(HashMap<String, Object> AreaByTwoPoints);

    NodeVO searchNodeById(String id);
    EdgeVO searchEdgeById(String id);

    void addNode(Map<String, Object> node);

    void addEdge(Map<String, Object> edge);

    void deleteNode(long nodeId);

    void deleteEdge(long edgeId);

    void setLocation(Map<String, Object> locationList);

    // 간선에 안전치 추가
    void setSafetyValue(Map<String, Object> safety);

    NodeVO getNode(long id);

    List<RoadVO> searchAllRoad();

    // 추정치 추가
    void setHeuristic(Map<String, Object> heuristicMap);
}
