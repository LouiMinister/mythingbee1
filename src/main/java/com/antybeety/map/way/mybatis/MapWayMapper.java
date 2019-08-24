package com.antybeety.map.way.mybatis;

import com.antybeety.map.way.model.vo.EdgeVO;
import com.antybeety.map.way.model.vo.NodeData;
import com.antybeety.map.way.model.vo.NodeVO;

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


}
