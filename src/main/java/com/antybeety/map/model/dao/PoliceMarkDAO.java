package com.antybeety.map.model.dao;
import com.antybeety.map.mybatis.MapMapper;
import com.antybeety.map.model.vo.FacilityMarkVO;
import com.antybeety.map.way.mybatis.MapWayMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value = "police")
//@Qualifier(value = "police")
public class PoliceMarkDAO extends FacilityMarkDAOImpl{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<FacilityMarkVO> searchFacilities(Map<String,Object> bounds){

        MapMapper mapper = sqlSession.getMapper(MapMapper.class);
        return mapper.selectPolice(bounds);
    }

    @Override
    public List<FacilityMarkVO> searchAll() {
        return null;
    }

    //모든 지구대의 위도, 경도 값을 리턴하는 메서드로,
    // ai data 셋팅 시, 사용
    @Override
    public List<FacilityMarkVO> getLatLng() {

        MapWayMapper mapper=  sqlSession.getMapper(MapWayMapper.class);

        return mapper.searchPatrolDivision();
    }
}
