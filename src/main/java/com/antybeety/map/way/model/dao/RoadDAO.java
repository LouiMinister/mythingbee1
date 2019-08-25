package com.antybeety.map.way.model.dao;

import com.antybeety.map.mybatis.MapMapper;
import com.antybeety.map.way.model.vo.RoadVO;
import com.antybeety.map.way.mybatis.MapWayMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoadDAO {

    private SqlSession sqlSession;

    public List<RoadVO> searchAllRoad(){
        MapWayMapper mapWayMapper = sqlSession.getMapper(MapWayMapper.class);
        return mapWayMapper.searchAllRoad();
    }
}
