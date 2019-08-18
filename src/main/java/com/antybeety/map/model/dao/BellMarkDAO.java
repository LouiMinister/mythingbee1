package com.antybeety.map.model.dao;

import com.antybeety.map.mybatis.MapMapper;
import com.antybeety.map.model.vo.FacilityMarkVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("BellMarkDAO")
public class BellMarkDAO extends FacilityMarkDAOImpl {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<FacilityMarkVO> searchFacilities(Map<String,Object> bounds) {
        MapMapper mapper = sqlSession.getMapper(MapMapper.class);
        return mapper.selectBell(bounds);
    }

    @Override
    public List<FacilityMarkVO> searchAll() {
        return null;
    }

    @Override
    public String getFacilName() {
        return "bell";
    }

}
