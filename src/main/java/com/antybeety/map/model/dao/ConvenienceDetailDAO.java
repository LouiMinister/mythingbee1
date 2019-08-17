package com.antybeety.map.model.dao;

import com.antybeety.map.model.vo.FacilityDetailVO;
import com.antybeety.map.mybatis.MapMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConvenienceDetailDAO  extends  FacilityDetailDAOImpl{

    @Autowired
    private SqlSession sqlSession_oracle;

    @Override
    public FacilityDetailVO searchDetail(String code) {
        MapMapper mapper = sqlSession_oracle.getMapper(MapMapper.class);
        FacilityDetailVO vo = mapper.getConvenienceDetail(code);
        //System.out.println(vo.toString());
        return vo;
    }
    @Override
    public String getFacilName() {
        return "CS";
    }
}
