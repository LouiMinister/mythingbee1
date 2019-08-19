package com.antybeety.map.model.dao;

import com.antybeety.map.mybatis.MapMapper;
import com.antybeety.map.model.vo.FacilityMarkVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ConvenienceMarkDAO extends FacilityMarkDAOImpl{

    @Autowired
    private SqlSession sqlSession_oracle;

    @Override
    public List<FacilityMarkVO> searchFacilities(Map<String,Object> bounds) {

        MapMapper mapper = sqlSession_oracle.getMapper(MapMapper.class);
        List<FacilityMarkVO> list = mapper.selectConvenience(bounds);

        //System.out.println(list);
        return list;
    }

    @Override
    public List<FacilityMarkVO> searchAll(){
        return null;
    }

    @Override
    public String getFacilName() {
        return "convenience";
    }
}
