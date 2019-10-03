package com.antybeety.map.way.model.dao;

import com.antybeety.map.way.model.vo.EdgeInfoVO;
import com.antybeety.map.way.mybatis.MapWayMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EdgeInfoDAO {
    @Autowired
    private SqlSession sqlSession;
    public List<EdgeInfoVO> getEdgeInfo(){
        MapWayMapper mapper = sqlSession.getMapper(MapWayMapper.class);
        return mapper.searchAllEdgeInfo();
    }
}
