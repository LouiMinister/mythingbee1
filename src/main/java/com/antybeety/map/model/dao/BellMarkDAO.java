package com.antybeety.map.model.dao;

import com.antybeety.map.mybatis.MapMapper;
import com.antybeety.map.model.vo.FacilityMarkVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value = "bell")
public class BellMarkDAO extends FacilityMarkDAOImpl implements InitializingBean, DisposableBean {

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
    public void destroy() throws Exception {
        System.out.println("BellDAO의 destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("BellDAO의 afterPropertieseSet");
    }

    //안쓰임
    @Override
    public List<FacilityMarkVO> getLatLng() {
        return null;
    }
}
