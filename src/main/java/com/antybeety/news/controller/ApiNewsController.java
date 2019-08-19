package com.antybeety.news.controller;

import com.antybeety.news.model.vo.ArticleInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class ApiNewsController {
    @Autowired
    private NewsController controller;


    @RequestMapping(value="/getArticles" ,method= RequestMethod.GET)
    public List<ArticleInfoVO> getArticles(@RequestParam String lastArticleCode){
        int limit= 5;
        //System.out.println(lastArticleCode);
        List<ArticleInfoVO>  articles=  controller.getArticles(lastArticleCode, limit);

        return articles;
    }

    @RequestMapping(value="/searchNews", method=RequestMethod.GET)
    public List<ArticleInfoVO> getArticles(@RequestParam String searchWord,
                                           @RequestParam(required =false) String lastArticleCode,
                                           @RequestParam String date,
                                           @RequestParam String district,
                                           @RequestParam(required = false) String mode){
        int limit = Integer.MAX_VALUE;
        List<ArticleInfoVO> articles=null;

        articles = controller.searchArticle(searchWord,lastArticleCode, date, district ,limit );
//        controller.searchArticle(searchWord,lastArticleCode,date,district,limit);
        //System.out.println("검색어:"+searchWord+"마지막기사코드"+lastArticleCode+"마지막일"+date+"구역"+district+"모드"+mode );
        //System.out.println(articles);
        return  articles;

    }

    @RequestMapping(value="/increaseViewCount",method=RequestMethod.GET)
    public @ResponseBody
    int addViewCount(@RequestParam(value="articleId") String arCode){
        //System.out.println(arCode + "조회수 증가");


        return controller.increaseViewCount(arCode);
    }

    @RequestMapping(value="/updateTopKeywords", method = RequestMethod.GET)
    public @ResponseBody
    List<String> updateTopKeywords(int limit){
//        limit=5;
        return controller.searchTopKeywords(limit);
    }

}
