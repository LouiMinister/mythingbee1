package com.antybeety.map.way.model.dao;

import com.antybeety.map.way.model.vo.EdgeVO;
import com.antybeety.map.mybatis.MapMapper;
import com.antybeety.map.way.model.vo.NodeVO;
import com.antybeety.map.way.mybatis.MapWayMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class EdgeDAO {

    public static final double MARGIN = 0.0005;

    @Autowired
    private SqlSession sqlSession;

    private MapWayMapper getMapper() {
        return sqlSession.getMapper(MapWayMapper.class);
    }

    public List<EdgeVO> getAllEdge(){

        MapWayMapper mapper = getMapper();

        return mapper.getAllEdge();
    }

    public void setLocation(Map<String, Object> locationList) {
        MapWayMapper mapWayMapper = getMapper();

        mapWayMapper.setLocation(locationList);
    }

    public void setSafetyValue(List<EdgeVO> edgeList) {
        MapWayMapper mapWayMapper = sqlSession.getMapper(MapWayMapper.class);

        Map<String, Object> safety = new HashMap<>();
        try {
            for (EdgeVO e : edgeList) {
                if (e.getSafeVal() == 0) continue;
                safety.put("id", e.getId());
                safety.put("safetyValue", e.getSafeVal());

                mapWayMapper.setSafetyValue(safety);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<EdgeVO> searchEdgesByArea(double lat1, double lng1, double lat2, double lng2) {
        MapWayMapper mapper = getMapper();
        // 1이 작아야됨 그래서 크면 바꿔줌
        if((lat1>lat2)){
            double temp = lat1;
            lat1= lat2;
            lat2= temp;
        }
        if((lng1>lng2)){
            double temp = lng1;
            lng1= lng2;
            lng2=temp;
        }
        HashMap param =new HashMap<String, Object>();
        param.put("lat1",lat1-MARGIN);
        param.put("lng1",lng1-MARGIN);
        param.put("lat2",lat2+MARGIN);
        param.put("lng2",lng2+MARGIN);
        return mapper.searchEdgesByArea(param);
    }

    public EdgeVO searchEdgeById(String id){
        MapWayMapper mapper = getMapper();
        return mapper.searchEdgeById(id);
    }
}
