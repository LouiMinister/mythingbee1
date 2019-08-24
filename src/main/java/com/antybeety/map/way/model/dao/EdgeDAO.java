package com.antybeety.map.way.model.dao;

import com.antybeety.map.way.model.vo.EdgeVO;
import com.antybeety.map.mybatis.MapMapper;
import com.antybeety.map.way.mybatis.MapWayMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class EdgeDAO {
    @Autowired
    private SqlSession sqlSession;

    public List<EdgeVO> getAllEdge(){

        MapWayMapper mapper = sqlSession.getMapper(MapWayMapper.class);

        return mapper.getAllEdge();
    }

    public void setLocation(Map<String, Object> locationList) {
        MapWayMapper mapWayMapper = sqlSession.getMapper(MapWayMapper.class);

        mapWayMapper.setLocation(locationList);
    }
}
