package com.antybeety.news.model.service;

import com.antybeety.news.model.vo.ArticleInfoKVO;
import org.springframework.stereotype.Service;

@Service
public class NewsCrawlingServiceImpl implements NewsCrawlingService {

    @Override
    public ArticleInfoKVO searchArticle(int engine, String url) {
        return null;
    }

    public ArticleInfoKVO searchAtDaum(String url){
        return null;
    }

}
