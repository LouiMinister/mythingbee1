package com.antybeety.map.way.model.dao;

import com.antybeety.map.way.model.vo.EdgeVO;
import com.antybeety.map.mybatis.MapMapper;
import com.antybeety.map.way.mybatis.MapWayMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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

    public void setSafetyValue(List<EdgeVO> edgeList) {
        MapWayMapper mapWayMapper = sqlSession.getMapper(MapWayMapper.class);

        Map<String,Object> safety = new HashMap<>();
        try{
            for(EdgeVO e : edgeList){
                if(e.getSafeVal()==0) continue;
                safety.put("id",e.getId());
                safety.put("safetyValue",e.getSafeVal());

                mapWayMapper.setSafetyValue(safety);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
