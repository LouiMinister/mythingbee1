package com.antybeety.map.way.model.dao;

import com.antybeety.map.way.model.vo.EdgeVO;
import com.antybeety.map.way.model.vo.NodeData;
import com.antybeety.map.mybatis.MapMapper;
import com.antybeety.map.way.model.vo.NodeVO;
import com.antybeety.map.way.mybatis.MapWayMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class NodeDAO {
    @Autowired
    private SqlSession sqlSession;


    private MapWayMapper getMapper() {
        return sqlSession.getMapper(MapWayMapper.class);
    }


    public List<NodeVO> getAllNode(){
        MapWayMapper mapper = sqlSession.getMapper(MapWayMapper.class);
        return mapper.getAllNode();
    }

    public List<NodeVO> searchNodesByArea(double lat1, double lng1, double lat2, double lng2) {
        MapWayMapper mapper = getMapper();
        if(!(lat1<lat2)){
            double temp = lat1;
            lat1= lat2;
            lat2= temp;
        }
        if(!(lng1<lng2)){
            double temp = lng1;
            lng1= lng2;
            lng2=temp;
        }
        HashMap param = new HashMap<String, Object>();
        param.put("lat1",lat1);
        param.put("lng1",lng1);
        param.put("lat2",lat2);
        param.put("lng2",lng2);
        return mapper.searchNodeByArea(param);
    }

    public NodeVO searchNodeById(String id){
        MapWayMapper mapper = getMapper();
        return mapper.searchNodeById(id);
    }
}
