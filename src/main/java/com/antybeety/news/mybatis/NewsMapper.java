package com.antybeety.news.mybatis;

import com.antybeety.news.model.vo.ArticleInfoVO;

import java.util.HashMap;
import java.util.List;


public interface NewsMapper {

    int addArticle(ArticleInfoVO article);

    ArticleInfoVO searchArticleInfo(String code);

    List<ArticleInfoVO> searchAllArticles();

    String searchArticleTimeByCode(String code);

    List<ArticleInfoVO> searchBeforeArticlesByTime(HashMap<String, Object> param);

    List<ArticleInfoVO> searchArticleByFilter(HashMap<String, Object> param);

    List<ArticleInfoVO> searchArticleByFilterDistrict(HashMap<String, Object> param);

    String searchLastDate();

    int updateArticle(HashMap<String,Object> param);

    int deleteArticle(String code);

    int increaseViewCount(String code);

    List<ArticleInfoVO> searchDelArticleInfo();

    int realDelArticle(String code);

    int restoreArticle(String code);
}
