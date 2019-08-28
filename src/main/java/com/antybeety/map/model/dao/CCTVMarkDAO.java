package com.antybeety.map.model.dao;

import com.antybeety.map.mybatis.MapMapper;
import com.antybeety.map.model.vo.FacilityMarkVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value = "cctv")
//@Qualifier(value = "cctv")
public class CCTVMarkDAO extends FacilityMarkDAOImpl{

    @Autowired
    private SqlSession sqlSession;


    @Override
    public List<FacilityMarkVO> searchFacilities(Map<String,Object> bounds) {

        MapMapper mapper = sqlSession.getMapper(MapMapper.class);
        return mapper.selectCCTV(bounds);
    }

    @Override
    public List<FacilityMarkVO> searchAll()  {
        return null;
    }
}
