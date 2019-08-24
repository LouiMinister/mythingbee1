package com.antybeety.map.way.mybatis;

import com.antybeety.map.way.model.vo.EdgeVO;
import com.antybeety.map.way.model.vo.NodeData;

import java.util.List;
import java.util.Map;

public interface MapWayMapper {
    List<NodeData> getAllNode();
    List<EdgeVO> getAllEdge();

    void addNode(Map<String, Object> node);
    void addEdge(Map<String, Object> edge);

    void deleteNode(Long index);
    void deleteEdge(Long index);
}
