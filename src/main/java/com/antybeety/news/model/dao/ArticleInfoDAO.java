package com.antybeety.news.model.dao;

import com.antybeety.news.model.vo.ArticleInfoKVO;
import com.antybeety.news.model.vo.ArticleInfoVO;


import java.util.HashMap;
import java.util.List;


public interface ArticleInfoDAO {

    int addArticle(ArticleInfoVO article);
    ArticleInfoVO searchArticleInfo(String code);
    List<ArticleInfoVO> searchAllArticles();
    String searchArticleTimeByCode(String code);
    List<ArticleInfoVO> searchBeforeArticlesByTime(String time, int limit);
    List<ArticleInfoVO> searchArticleByFilter(String searchWord, String startTime, String lastTime, int limit);
    List<ArticleInfoVO> searchArticleByFilter(String searchWord, String startTime, String lastTime, String district, int limit);
    int updateArticle(ArticleInfoKVO article);
    int removeArticle(String code);
    String searchLastDate();

    int increaseViewCount(String code);

    List<ArticleInfoVO> searchDelArticleInfo();

    int realDelArticle(String code);

    int restoreArticle(List<String> code);

}
