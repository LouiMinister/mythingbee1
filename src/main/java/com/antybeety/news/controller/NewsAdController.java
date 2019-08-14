package com.antybeety.news.controller;

import com.antybeety.news.model.vo.ArticleInfoKVO;
import com.antybeety.news.model.vo.ArticleInfoVO;
import com.antybeety.press.model.vo.PressVO;

import java.util.List;

public interface NewsAdController {

    /*ArticleVO를 통해 기사를 추가
     * int로 결과 반환. 결과는 성공했을 시 1*/
    int addArticle(ArticleInfoKVO article);

    /*기사의 코드를 통해 해당하는 기사의 기사 VO를 리턴받음*/
    ArticleInfoVO searchArticle(String artiCode);

    /*모든 기사 불러오기*/
    List<ArticleInfoVO> searchAllArticles();

    /*기사 VO를 통해 기사의 내용을 업데이트함*/
    int updateArticle(ArticleInfoKVO article);

    /*기사들의 코드들을 컬랙션으로 받아 해당하는 기사를 삭제함*/
    int deleteArticle(List<String> articles);

    /*언론사 추가하기*/
    int insertPress(PressVO vo);

    /*언론사 수정하기*/
    int updatePress(String code, String name);

    /*언론사 삭제하기*/
    int deletePress(String name);

    /*모든 언론사의 이름들을 불러옴*/
    List<String> searchAllPresses();

    /*모든 지역구 이름들을 불러옴*/
    List<String> searchAllDistricts();






}
