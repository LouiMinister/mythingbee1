package com.antybeety.map.model.dao;

import com.antybeety.map.model.vo.FacilityMarkVO;
import com.antybeety.map.mybatis.MapMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
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

    @Override
    public String getFacilName() {
        return "protectHouse";
    }
}
