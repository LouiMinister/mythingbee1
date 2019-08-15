package com.antybeety.news.controller;

import com.antybeety.news.model.vo.ArticleInfoKVO;
import com.antybeety.news.model.vo.ArticleInfoVO;
import com.antybeety.news.model.vo.KeywordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class ViewAdNewsController {
    @Autowired
    private NewsAdController newsAdController;

    @RequestMapping(value = "/menu",method= RequestMethod.GET)
    public String admin(){
        return "admin/adminMenu";
    }

    @RequestMapping(value= "/facility" ,method = RequestMethod.GET)
    public String facilityPage(){
        return "admin_map/facilityAdmin";
    }

    @RequestMapping(value="/news",method=RequestMethod.GET)
    public String getNewsPage(){
        return "admin_news/newsAdmin";
    }

    @RequestMapping(value = "/getNews",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public @ResponseBody
    List<ArticleInfoVO> newsPage(){
        //기사 전부 가져와서 리스트 형태로 반환
        List<ArticleInfoVO> article = newsAdController.searchAllArticles();

        return article;
    }

    @RequestMapping(value="/addArticle" , method=RequestMethod.GET)
    public ModelAndView goAddArticle(){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin_news/insertArticle");

        //모든 지역구 이름 정보
        //미구현
        List<String> districts = newsAdController.searchAllDistricts();
        System.out.println(districts);

        //모든 언론사 이름 정보
        List<String> presses = newsAdController.searchAllPresses();
        System.out.println(presses);

        mav.addObject("districts",districts);
        mav.addObject("presses",presses);

        return mav;
    }

    @RequestMapping(value="/addArticle" , method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public @ResponseBody int addArticle(@RequestBody Map<String, Object> requestParam){


        //int addArticle(ArticleInfoKVO article)
        //System.out.println("기사 추가");
        ArticleInfoKVO article = new ArticleInfoKVO();

        article.setTitle((String)requestParam.get("title"));
        article.setSummary((String)requestParam.get("summary"));
        article.setUrl((String)requestParam.get("url"));
        article.setImgURL((String)requestParam.get("imgUrl"));
        article.setDistrictName("관악구");
        article.setPressName((String)requestParam.get("pressName"));

        System.out.println(requestParam.get("keywordName"));
        System.out.println(requestParam.get("keywordCode"));


        String[] keywordNameSplit = requestParam.get("keywordName").toString().split(",");
        String[] keywordCodeSplit = requestParam.get("keywordCode").toString().split(",");

        List<KeywordVO> keywords = new ArrayList<KeywordVO>();

        for(int i=0;i<keywordCodeSplit.length;i++){
            keywords.add(new KeywordVO(keywordNameSplit[i],keywordCodeSplit[i]));
        }

        article.setKeywords(keywords);

        return newsAdController.addArticle(article);
    }

    @RequestMapping(value="/addPress",method=RequestMethod.GET)
    public String goAddPress(){
        return "admin_news/insertPress";
    }

//    언론사 추가 페이지에서 이어서 구현
//    @RequestMapping(value="/addPress",method=RequestMethod.POST)
//    public void addPress(@RequestParam(value = "code") String code,
//                         @RequestParam(value="name") String name){
//
//        int result = newsService.insertPressInfo(code, name);
//
//        newsPage();
//    }

    @RequestMapping(value="/updateNews",method=RequestMethod.GET)
    public ModelAndView goUpdatePage(@RequestParam("arCode") String arCode){
        System.out.println("뉴스 업데이트 페이지 : " + arCode);

        ModelAndView mav = new ModelAndView();

        //기사 코드와 일치하는 기사를 리턴한다.
        ArticleInfoVO article = newsAdController.searchArticle(arCode);
//        System.out.println(article);

        //기사에 해당하는 키워드
        List<String> keywords = new ArrayList<String>();
        String[] ary = article.getKeywordName().split(",");

        for(String s : ary){
            keywords.add(s);
        }

        //모든 지역구 이름 정보
        List<String> districts = newsAdController.searchAllDistricts();

        //모든 언론사 이름 정보
        List<String> presses = newsAdController.searchAllPresses();

        mav.addObject("article",article);
        mav.addObject("keywords",keywords);
        mav.addObject("districts",districts);
        mav.addObject("presses",presses);

        mav.setViewName("admin_news/updateArticle");

        return mav;
    }

//    뉴스 수정에서 구현
//    @RequestMapping(value="/updateNews",method=RequestMethod.POST)
//    public void updateNews(@RequestParam("arCode") String arCode){
//
//        //뉴스 페이지로
//        newsPage();
//    }

    @RequestMapping(value="/deleteArticles",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
    public @ResponseBody int removeArticles(@RequestParam(value = "delCodes[]") List<String> delCodes){
//        for(int i= 0 ;i<delCodes.size();i++){
//            System.out.println(delCodes.get(i));
//        }

        return delCodes.size();
    }
}