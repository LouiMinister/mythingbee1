package com.antybeety.map.way.model.dao;

import com.antybeety.map.way.mybatis.MapWayMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AiDataDAO {

    @Autowired
    private SqlSession sqlSession;

    public void addFacility(double edgeId, String facilityId){
        MapWayMapper mapWayMapper = sqlSession.getMapper(MapWayMapper.class);

        Map<String,Object> addFacilityMap = new HashMap<>();
        addFacilityMap.put("edgeId",edgeId);
        addFacilityMap.put("facilityId",facilityId.substring(0,2));
        mapWayMapper.addFacility(addFacilityMap);
    }
    public void addRoadInfo(double edgeId, int landType, int roadType, int safeRate){
        MapWayMapper mapWayMapper = sqlSession.getMapper(MapWayMapper.class);
        Map<String,Object> roadInfo = new HashMap<>();
        roadInfo.put("edgeId",edgeId);
        roadInfo.put("landType",landType);
        roadInfo.put("roadType",roadType);
        roadInfo.put("safeRate",safeRate);
        mapWayMapper.addRoadInfo(roadInfo);
    }
}
