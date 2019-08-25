package com.antybeety.map.way.model.dao;

import com.antybeety.map.way.mybatis.MapWayMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class HeuristicDAO {

    @Autowired
    private SqlSession sqlSession;

    public void setHeuristic(double startNodeId, double endNodeId, double heuristic){
        MapWayMapper mapWayMapper = sqlSession.getMapper(MapWayMapper.class);

        Map<String,Object> heuristicMap = new HashMap<>();
        heuristicMap.put("startNodeId",startNodeId);
        heuristicMap.put("endNodeId",endNodeId);
        heuristicMap.put("heuristic",heuristic);

        mapWayMapper.setHeuristic(heuristicMap);
    }
}
