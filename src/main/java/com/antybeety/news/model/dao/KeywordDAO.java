package com.antybeety.news.model.dao;

import com.antybeety.news.model.vo.ArticleInfoKVO;
import com.antybeety.news.model.vo.KeywordVO;
import com.antybeety.news.mybatis.KeywordMapper;
import com.antybeety.news.mybatis.NewsMapper;
import com.mysql.cj.xdevapi.SqlDataResult;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class KeywordDAO {

    @Autowired
    private SqlSession sqlSession;

    private KeywordMapper getMapper(){
        return sqlSession.getMapper(KeywordMapper.class);
    }

    public int addKeyword(String code, KeywordVO keyword){
        KeywordMapper mapper = getMapper();
        String preCode="";

        preCode= searchCodeByName(keyword.getName()); //같은 이름의 키워드가 존재하면 코드리턴, 없으면 ""리턴
        if(!preCode.equals("")){ //코드가 ""이 아니면 같은 이름의 키워드가 존재함.

        }

        String lastIndexCode = searchCode(code);


        int res = mapper.addKeyword(keyword);
        return 0;
    }

    /*그 코드를 포함한 마지막 기사의 코드 찾기*/
    public String searchCode(String code){
        KeywordMapper mapper = getMapper();

        return mapper.searchCode(code);
    }

    /*키워드 이름을 통해 코드 찾기. 이름이 존재하다면 해당하는 키워드의 코드를 리턴. 없다면 ""을 리턴*/
    public String searchCodeByName(String name){
        KeywordMapper mapper = getMapper();
        return mapper.searchCodeByName(name);

    }
}
