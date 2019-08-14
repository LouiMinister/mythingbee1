package com.antybeety.news.controller;

import com.antybeety.news.model.vo.ArticleInfoKVO;
import com.antybeety.news.model.vo.ArticleInfoVO;

import java.util.List;

public interface NewsAdController {

    /*ArticleVO를 통해 기사를 추가
     * int로 결과 반환. 결과는 성공했을 시 1*/
    int addArticle(ArticleInfoKVO article);

    /*기사의 코드를 통해 해당하는 기사의 기사 VO를 리턴받음*/
    ArticleInfoVO searchArticle(String artiCode);

    /*모든 언론사의 이름들을 불러옴*/
    List<String> searchAllPresses();

    /*모든 지역구 이름들을 불러옴*/
    List<String> searchAllDistricts();

    /*기사 VO를 통해 기사의 내용을 업데이트함*/
    int updateArticle(ArticleInfoKVO article);
}
