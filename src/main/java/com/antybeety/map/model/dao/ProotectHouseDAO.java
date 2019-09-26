package com.antybeety.map.model.dao;

import com.antybeety.map.model.vo.FacilityMarkVO;
import com.antybeety.map.mybatis.MapMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository (value = "protectHouse")
//@Qualifier(value = "protectHouse")
public class ProotectHouseDAO extends FacilityMarkDAOImpl{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<FacilityMarkVO> searchFacilities(Map<String, Object> bounds) {
        MapMapper mapper = sqlSession.getMapper(MapMapper.class);
        return mapper.selectProtectHouse(bounds);
    }

    @Override
    public List<FacilityMarkVO> searchAll() {
        return null;
    }

    //사용하지 않음.
    @Override
    public List<FacilityMarkVO> getLatLng() {
        return null;
    }
}
