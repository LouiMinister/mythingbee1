package com.antybeety.news.model.service;

import com.antybeety.news.model.vo.ArticleInfoKVO;
import com.antybeety.news.model.vo.ArticleInfoVO;
import com.antybeety.press.model.vo.PressVO;

import java.util.List;

public interface NewsAdService {

    /*Vo로 기사 추가*/
    public int addArticle(ArticleInfoKVO article);

    /*기사코드 만들기*/
    public String makeArticleCode();

    /*기사코드 하나로 기사찾기*/
    public ArticleInfoVO searchArticle(String article);

    /*기사코드 여러개로 기사 삭제하기*/
    public int deleteArticles(List<String> code);

    /*모든 지역구 찾기*/
    public List<String> searchAllDistricts();

    /* ArticleInfoKVO로 지역구 수정하기기*/
   public int updateArticle(ArticleInfoKVO article);

   /*모든 기사 찾기*/
    public List<ArticleInfoVO> searchAllArticles();



}
