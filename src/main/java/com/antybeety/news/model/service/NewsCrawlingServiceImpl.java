package com.antybeety.news.model.service;

import com.antybeety.news.model.vo.ArticleInfoKVO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class NewsCrawlingServiceImpl implements NewsCrawlingService {

    @Override
    public ArticleInfoKVO searchArticle(int engine, String url) {
        ArticleInfoKVO article=null;
        switch (engine){
            case NewsCrawlingService.ENGINE_DAUM:
                article =searchAtDaum(url);
                break;
            case NewsCrawlingService.ENGINE_NAVER:
                article= searchAtNaver(url);
                break;
            default:
                article=null;
                break;
        }
        return article;
    }

    public ArticleInfoKVO searchAtDaum(String url){
        Document doc;
        try{
           doc = Jsoup.connect(url).get();
        }catch(Exception e){
            return null;
        }

        //기사 제목
        String title = doc.select("h3.tit_view").text();
        //기사 요약
        String summary = doc.select("div.layer_summary > *:not(.ico_info_summary)").text();
        if (summary.equals("")){    // 요약내용을 지원하지 않는 기사일 경우, 전체 기사에서 첫 문장을 가져온다
            summary= doc.select("p[dmcf-ptype=general]").first().text();
        }
        //기사 이미지
        String imgUrl = doc.select("img.thumb_g_article").attr("src");
        //언론사
        String press = doc.select("meta[name=article:media_name]").attr("content");

        //기사 데이터를 담을 VO
        ArticleInfoKVO article= new ArticleInfoKVO();
        article.setTitle(title);
        article.setPressName(press);
        article.setSummary(summary);
        article.setUrl(url);
        article.setImgURL(imgUrl);

        return article;
    }

    public ArticleInfoKVO searchAtNaver(String url){
        return null;
    }

}
