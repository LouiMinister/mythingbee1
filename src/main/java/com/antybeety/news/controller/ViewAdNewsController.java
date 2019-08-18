package com.antybeety.news.controller;

import com.antybeety.news.model.service.NewsCrawlingService;
import com.antybeety.news.model.vo.ArticleInfoKVO;
import com.antybeety.news.model.vo.ArticleInfoVO;
import com.antybeety.news.model.vo.KeywordVO;
import com.antybeety.press.model.vo.PressVO;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Log4j
@Controller
@RequestMapping("/admin")
public class ViewAdNewsController {
    @Autowired
    private NewsAdController newsAdController;

    //로그인 페이지 요청
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String adminIndex() {
        return "admin/adminLogin";
    }

    //produces="application/json;charset=UTF-8"
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public @ResponseBody
//    String adminLogin(@RequestParam(value = "id", required = true) String id,
//                      @RequestParam(value = "password", required = true) String password) {
//        String pass = "verified";
//        String result = "/admin/news";
//
//        if (pass.equals(newsAdController.login(id, password))) {
//            return result;
//        } else {
//            System.out.println("로그인 실패");
//            return null;
//        }
//    }
//
//    @RequestMapping(value ="/accessError")
//    public ModelAndView accessDenied(Authentication auth, Model model){
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("/error");
//        log.info("Access Denied: "+ auth);
//        mav.addObject("msg","Access Denied");
//        return mav;
//    }






    //로그인 요청 처리
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    String adminLogin(@RequestParam(value = "id", required = true) String id,
                      @RequestParam(value = "password", required = true) String password) {
        String pass = "verified";
        String result = "/admin/news";

        if (pass.equals(newsAdController.login(id, password))) {
            return result;
        } else {
            return null;
        }
    }

    //시설물 관리자 페이지 연결
    @RequestMapping(value = "/facility", method = RequestMethod.GET)
    public String facilityPage() {
        return "admin_map/facilityAdmin";
    }

    //뉴스 관리자 페이지로 연결
    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getNewsPage() {
        return "admin_news/newsAdmin";
    }

    //뉴스 기사 전부 찾아와서 리턴
    @RequestMapping(value = "/getNews", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    List<ArticleInfoVO> newsPage() {
        //기사 전부 가져와서 리스트 형태로 반환
        return getAllArticleInfosVO();
    }

    //기사 크롤링해서 정보 요청
    @RequestMapping(value = "/addlink", method = RequestMethod.POST)
    public @ResponseBody ArticleInfoKVO parsing(@RequestParam(value = "link", required = true) String url) {
        return  newsAdController.crawlingArticle(1,url);
    }

    //기사 추가 페이지 요청
    @RequestMapping(value="/addArticle" , method=RequestMethod.GET)
    public ModelAndView goAddArticle(){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin_news/insertArticle");

        //모든 지역구 이름 정보
        List<String> districts = getAllDistrictName();

        //모든 언론사 이름 정보
        List<String> presses = getAllPressName();

        mav.addObject("districts",districts);
        mav.addObject("presses",presses);

        return mav;
    }

    //기사 추가 요청
    @RequestMapping(value="/addArticle" , method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public @ResponseBody int addArticle(@RequestBody Map<String, Object> requestParam){

        ArticleInfoKVO article = new ArticleInfoKVO();

        article.setTitle((String)requestParam.get("title"));
        article.setSummary((String)requestParam.get("summary"));
        article.setUrl((String)requestParam.get("url"));
        article.setImgURL((String)requestParam.get("imgUrl"));
        article.setDistrictName((String)requestParam.get("districtName"));
        article.setPressName((String)requestParam.get("pressName"));

        String[] keywordNameSplit = requestParam.get("keywordName").toString().split(",");
        String[] keywordCodeSplit = requestParam.get("keywordCode").toString().split(",");

        List<KeywordVO> keywords = new ArrayList<KeywordVO>();

        for(int i=0;i<keywordCodeSplit.length;i++){
            keywords.add(new KeywordVO(keywordNameSplit[i],keywordCodeSplit[i]));
        }

        article.setKeywords(keywords);

        return newsAdController.addArticle(article);
    }

    //언론사 추가 페이지 요청
    @RequestMapping(value="/addPress",method=RequestMethod.GET)
    public ModelAndView goAddPress(){
        ModelAndView mav = new ModelAndView();

        List<String> presses = getAllPressName();

        mav.addObject("presses",presses);

        mav.setViewName("admin_news/insertPress");


        return mav;
    }

    //언론사 추가 요청
    @RequestMapping(value="/addPress",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public @ResponseBody int addPress(@RequestBody Map<String, Object> requestParam){
        String name = (String)requestParam.get("name");
        String code = (String)requestParam.get("code");

        return newsAdController.insertPress(new PressVO(code, name));
    }

    //뉴스 업데이트 페이지 요청
    @RequestMapping(value="/updateNews",method=RequestMethod.GET)
    public ModelAndView goUpdatePage(@RequestParam("arCode") String arCode){

        ModelAndView mav = new ModelAndView();

        //기사 코드와 일치하는 기사를 리턴한다.
        ArticleInfoKVO article = newsAdController.searchArticle(arCode);

        //기사에 해당하는 키워드
        List<KeywordVO> keywords = article.getKeywords();

        //System.out.println(keywords);
        //모든 지역구 이름 정보
        List<String> districts = getAllDistrictName();

        //모든 언론사 이름 정보
        List<String> presses = getAllPressName();

        mav.addObject("article",article);
        mav.addObject("keywords",keywords);
        mav.addObject("districts",districts);
        mav.addObject("presses",presses);

        mav.setViewName("admin_news/updateArticle");

        return mav;
    }

    //기사 업데이트 요청
    @RequestMapping(value="/updateNews",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public @ResponseBody int updateNews(@RequestBody Map<String, Object> requestParam){

        ArticleInfoKVO article = new ArticleInfoKVO();

        article.setCode((String)requestParam.get("arCode"));
        article.setTitle((String)requestParam.get("title"));
        article.setSummary((String)requestParam.get("summary"));
        article.setUrl((String)requestParam.get("url"));
        article.setImgURL((String)requestParam.get("imgUrl"));
        article.setDistrictName((String)requestParam.get("districtName"));
        article.setPressName((String)requestParam.get("pressName"));

        String[] keywordNameSplit = requestParam.get("keywordName").toString().split(",");
        String[] keywordCodeSplit = requestParam.get("keywordCode").toString().split(",");

        List<KeywordVO> keywords = new ArrayList<KeywordVO>();

        for(int i=0;i<keywordCodeSplit.length;i++){
            keywords.add(new KeywordVO(keywordNameSplit[i],keywordCodeSplit[i]));
        }

        article.setKeywords(keywords);

        //System.out.println(article);

        return newsAdController.updateArticle(article);
    }

    //기사 삭제 요청  --> 기사 삭제 표시 컬럼 바꿈
    @RequestMapping(value="/deleteArticles",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
    public @ResponseBody int removeArticles(@RequestParam(value = "delCodes[]") List<String> delCodes){

        return newsAdController.deleteArticle(delCodes);
    }

    //휴지통의 기사를 실제 디비에서 삭제
    @RequestMapping(value = "/realDelArticles",method=RequestMethod.GET)
    public @ResponseBody int removeArticlesFromDB(@RequestParam(value="delCodes[]") List<String>delCodes){
        return newsAdController.realDelArticles(delCodes);
    }

    //기사 삭제 휴지통 페이지 요청
    @RequestMapping(value="/goTrashCan",method = RequestMethod.GET)
    public String goTrashCan(){
        return "admin_news/trashcan";
    }

    //삭제 표시 되어있는 기사만 가져오기
    @RequestMapping(value="/getDelArticleInfos",method = RequestMethod.GET)
    public @ResponseBody List<ArticleInfoVO> getDelArticles(){
        return newsAdController.searchDelArticleInfo();
    }

    //휴지통에 있는 기사 목록으로 복구
    @RequestMapping(value="/restoreAr",method=RequestMethod.GET)
    public @ResponseBody int restoreArticle(@RequestParam(value="arCode")String code){
        return newsAdController.restoreArticle(code);
    }

    //언론사 삭제 요청
    @RequestMapping(value="/deletePress", method=RequestMethod.GET,produces="application/json;charset=UTF-8")
    public @ResponseBody int removeArticles(@RequestParam(value="delPressName") String delPressName){

        return newsAdController.deletePress(delPressName);
    }

    //모든 언론사 이름 정보 요청
    @RequestMapping(value="/allPress", method=RequestMethod.GET)
    public @ResponseBody List<String> getPressNamePAGE(){
        return getAllPressName();
    }

    //모든 언론사 이름 정보 리턴
    private List<String> getAllPressName(){
        return newsAdController.searchAllPresses();
    }

    //모든 지역구 이름 정보 리턴
    private List<String> getAllDistrictName(){
        return newsAdController.searchAllDistricts();
    }

    private List<ArticleInfoVO> getAllArticleInfosVO() {return newsAdController.searchAllArticles();}

}