package com.antybeety.map.model.dao;

import com.antybeety.map.model.vo.RoadVO;
import com.antybeety.map.mybatis.MapMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class RoadDAO {
    @Autowired
    private SqlSession sqlSession;

    public List<RoadVO> getAll(){

        MapMapper mapper = sqlSession.getMapper(MapMapper.class);

        return mapper.getAllRoad();
    }
}
