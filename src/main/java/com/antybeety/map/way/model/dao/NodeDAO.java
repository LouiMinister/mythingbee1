package com.antybeety.map.way.model.dao;

import com.antybeety.map.way.model.vo.NodeData;
import com.antybeety.map.mybatis.MapMapper;
import com.antybeety.map.way.mybatis.MapWayMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NodeDAO {
    @Autowired
    private SqlSession sqlSession;

    public List<NodeData> getAllNode(){

        MapWayMapper mapper = sqlSession.getMapper(MapWayMapper.class);

        return mapper.getAllNode();
    }
}
