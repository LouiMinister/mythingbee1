package com.antybeety.press.model.dao;

import com.antybeety.news.model.vo.ArticleInfoVO;
import com.antybeety.news.mybatis.NewsMapper;
import com.antybeety.press.model.vo.PressVO;
import com.antybeety.press.mybatis.PressMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class PressDAO {

    @Autowired
    private SqlSession sqlSession;

    private PressMapper getMapper(){
        return sqlSession.getMapper(PressMapper.class);
    }
    int insertPressInfo(PressVO press){
        PressMapper mapper = getMapper();
        HashMap<String, Object> param = new HashMap<>();
        param.put("code", press.getCode());
        param.put("name", press.getName());
        return mapper.insertPressInfo(param);
    }

    public String searchPressCode(String name) {
        PressMapper mapper = getMapper();
        String info = mapper.searchPressCode(name);
        return info;
    }

    public String searchPressName(String code) {
        PressMapper mapper = getMapper();
        String info = mapper.searchPressName(code);
        return info;
    }

    public List<String> searchAllNames() {
        PressMapper mapper = getMapper();
        List<String> info = mapper.searchAllNames();
        return info;
    }
}
