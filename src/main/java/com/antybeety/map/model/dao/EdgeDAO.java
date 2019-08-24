package com.antybeety.map.model.dao;

import com.antybeety.map.model.vo.EdgeVO;
import com.antybeety.map.mybatis.MapMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EdgeDAO {
    @Autowired
    private SqlSession sqlSession;

    public List<EdgeVO> getAllEdge(){

        MapMapper mapper = sqlSession.getMapper(MapMapper.class);

        return mapper.getAllEdge();
    }
}
