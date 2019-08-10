package com.antybeety.district.model.dao;

import com.antybeety.district.mybatis.DistrictMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DistrictDAO {
    @Autowired
    private SqlSession sqlSession;

    public DistrictDAO(){

    }

    private DistrictMapper getMapper(){
        return sqlSession.getMapper(DistrictMapper.class);
    }

    public List<String> searchAllDistrictNames(){
        DistrictMapper mapper= getMapper();
        List<String> infos =mapper.searchAllDistrictNames();
        return infos;
    }

    public List<String> searchAllDistrictCodes(){
        DistrictMapper mapper= getMapper();
        List<String> infos = mapper.searchAllDistrictCodes();
        return infos;
    }

    public String searchDistrictNameByCode(String code){
        DistrictMapper mapper= getMapper();
        String info = mapper.searchDistrictNameByCode(code);
        return info;
    }

    public String searchDistrictCodeByName(String name){
        DistrictMapper mapper= getMapper();
        String info = mapper.searchDistrictCodeByName(name);
        return info;
    }
}
