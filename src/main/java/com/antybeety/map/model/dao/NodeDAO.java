package com.antybeety.map.model.dao;

import com.antybeety.map.model.vo.NodeData;
import com.antybeety.map.mybatis.MapMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NodeDAO {
    @Autowired
    private SqlSession sqlSession;

    public List<NodeData> getAllNode(){

        MapMapper mapper = sqlSession.getMapper(MapMapper.class);

        return mapper.getAllNode();
    }
}
