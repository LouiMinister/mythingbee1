package com.antybeety.map.way.mybatis;

import com.antybeety.map.way.model.vo.EdgeVO;
import com.antybeety.map.way.model.vo.NodeData;
import com.antybeety.map.way.model.vo.NodeVO;

import java.util.List;
import java.util.Map;

public interface MapWayMapper {
    List<NodeVO> getAllNode();
    List<NodeData> getAllNodeData();
    List<EdgeVO> getAllEdge();


    void addNode(Map<String, Object> node);
    void addEdge(Map<String, Object> edge);

    void deleteNode(long nodeId);
    void deleteEdge(long edgeId);

    void setLocation(Map<String, Object> locationList);
}
