package com.antybeety.news.mybatis;

import com.antybeety.news.model.vo.KeywordVO;

import java.util.HashMap;
import java.util.List;

public interface KeywordMapper {

    //키워드 테이블에 키워드명, 키워드코드로 기사 추가
    int addKeyword(KeywordVO vo);

    //피벗 테이블에 기사코드, 키워드코드로 레코드 추가
    int addKeywordPivot(HashMap<String, String> param);

    //코드로 기사 찾아서 마지막 인덱스으 키워드 코드 리턴
    String searchCode(String code);

    //이름으로 기사 찾아서 마지막 인덱스의 키워드 코드 리턴
    String searchCodeByName(String name);

    /*가장 조회수 높은 키워드 n개를 리턴*/
    List<String> searchTopKeywords(int limit);

    //키워드 코드가 있는 피벗테이블의 레코드를 삭제
    int deleteKeyword(String code);

    //키워드코드와
    int cutKeywordBtwArticle(HashMap<String,Object> param);
    int cutAllKeywordBtwArticle(String arCode);
}