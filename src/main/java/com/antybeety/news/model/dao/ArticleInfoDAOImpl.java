package com.antybeety.news.model.dao;

import com.antybeety.news.model.vo.ArticleInfoKVO;
import com.antybeety.news.mybatis.NewsMapper;
import com.antybeety.news.model.vo.ArticleInfoVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ArticleInfoDAOImpl implements ArticleInfoDAO{


    @Autowired
    private SqlSession sqlSession;

    public ArticleInfoDAOImpl() {

    }

    private NewsMapper getMapper(){
        return sqlSession.getMapper(NewsMapper.class);
    }

    @Override
    public int addArticle(ArticleInfoVO article) {
        NewsMapper mapper=getMapper();
        return mapper.addArticle(article);
    }

    @Override
    public List<ArticleInfoVO> searchAllArticles() {
        NewsMapper mapper = getMapper();
        List<ArticleInfoVO> info = mapper.searchAllArticles();
        return info;
    }
    @Override
    public ArticleInfoVO searchArticleInfo(String code) {

        NewsMapper mapper = getMapper();
        ArticleInfoVO  info = mapper.searchArticleInfo(code);
        return info;
    }
    @Override
    public String searchArticleTimeByCode(String code) {
        NewsMapper mapper = getMapper();
        String time = mapper.searchArticleTimeByCode(code);
        return time;
    }
    @Override
    public List<ArticleInfoVO> searchBeforeArticlesByTime(String time, int limit) {
        NewsMapper mapper = getMapper();
        HashMap<String,Object> param = new HashMap<String,Object>();
        param.put("time", time);
        param.put("limit",limit);
        List<ArticleInfoVO> articles = mapper.searchBeforeArticlesByTime(param);
        return articles;
    }
    @Override
    public List<ArticleInfoVO> searchArticleByFilter(String searchWord, String startTime, String lastTime, int limit) {
        NewsMapper mapper = getMapper();
        HashMap<String,Object> param = new HashMap<String,Object>();
        param.put("searchWord", searchWord);
        param.put("startTime", startTime);
        param.put("lastTime",lastTime);
        param.put("limit",limit);
        List<ArticleInfoVO> articles = mapper.searchArticleByFilter(param);
        return articles;
    }
    @Override
    public List<ArticleInfoVO> searchArticleByFilter(String searchWord, String startTime, String lastTime, String district, int limit) {
        NewsMapper mapper = getMapper();
        HashMap<String,Object> param = new HashMap<String,Object>();
        param.put("searchWord", searchWord);
        param.put("startTime", startTime);
        param.put("lastTime",lastTime);
        param.put("district", district);
        param.put("limit",limit);
        List<ArticleInfoVO> articles =mapper.searchArticleByFilterDistrict(param);
        return articles;
    }

    @Override
    public String searchLastDate(){
        NewsMapper mapper = getMapper();
        String info = mapper.searchLastDate();
        return info;
    }

    @Override
    public int updateArticle(ArticleInfoKVO article) {
        NewsMapper mapper = getMapper();
        HashMap<String,Object> param = new HashMap<String,Object>();
        param.put("arCode",article.getCode());
        param.put("summary",article.getSummary());
        param.put("title",article.getTitle());
        param.put("url",article.getUrl());
        return mapper.updateArticle(param);
    }

    @Override
    public int removeArticle(String code) {
        NewsMapper mapper = getMapper();
        return mapper.deleteArticle(code);
    }
    


}
