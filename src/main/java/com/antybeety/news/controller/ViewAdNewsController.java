package com.antybeety.news.controller;

import com.antybeety.news.model.service.NewsCrawlingService;
import com.antybeety.news.model.vo.ArticleInfoKVO;
import com.antybeety.news.model.vo.ArticleInfoVO;
import com.antybeety.news.model.vo.KeywordVO;
import com.antybeety.press.model.vo.PressVO;
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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String adminIndex() {
        return "admin/adminLogin";
    }

    //produces="application/json;charset=UTF-8"
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    String adminLogin(@RequestParam(value = "id", required = true) String id,
                      @RequestParam(value = "password", required = true) String password) {
        String pass = "verified";
        String result = "/admin/news";

        if (pass.equals(newsAdController.login(id, password))) {
            return result;
        } else {
            System.out.println("로그인 실패");
            return null;
        }
    }

    @RequestMapping(value = "/facility", method = RequestMethod.GET)
    public String facilityPage() {
        return "admin_map/facilityAdmin";
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getNewsPage() {
        return "admin_news/newsAdmin";
    }

    @RequestMapping(value = "/getNews", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    List<ArticleInfoVO> newsPage() {
        //기사 전부 가져와서 리스트 형태로 반환
        List<ArticleInfoVO> article = newsAdController.searchAllArticles();

        return article;
    }

    @RequestMapping(value = "/addlink", method = RequestMethod.POST)
    public @ResponseBody ArticleInfoKVO parsing(@RequestParam(value = "link", required = true) String url) {
        return  newsAdController.crawlingArticle(1,url);
    }

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

    @RequestMapping(value="/addPress",method=RequestMethod.GET)
    public ModelAndView goAddPress(){
        ModelAndView mav = new ModelAndView();

        List<String> presses = getAllPressName();

        mav.addObject("presses",presses);

        mav.setViewName("admin_news/insertPress");


        return mav;
    }

    @RequestMapping(value="/addPress",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public @ResponseBody int addPress(@RequestBody Map<String, Object> requestParam){
        String name = (String)requestParam.get("name");
        String code = (String)requestParam.get("code");

        return newsAdController.insertPress(new PressVO(code, name));
    }

    @RequestMapping(value="/updateNews",method=RequestMethod.GET)
    public ModelAndView goUpdatePage(@RequestParam("arCode") String arCode){
        System.out.println("뉴스 업데이트 페이지 : " + arCode);

        ModelAndView mav = new ModelAndView();

        //기사 코드와 일치하는 기사를 리턴한다.
        ArticleInfoKVO article = newsAdController.searchArticle(arCode);

//        System.out.println(article.getKeywords());
        //기사에 해당하는 키워드
        List<KeywordVO> keywords = article.getKeywords();

        System.out.println(keywords);
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
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

        System.out.println(article);

        return newsAdController.updateArticle(article);
    }

    @RequestMapping(value="/deleteArticles",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
    public @ResponseBody int removeArticles(@RequestParam(value = "delCodes[]") List<String> delCodes){

        return newsAdController.deleteArticle(delCodes);
    }

    @RequestMapping(value="/deletePress", method=RequestMethod.GET,produces="application/json;charset=UTF-8")
    public @ResponseBody int removeArticles(@RequestParam(value="delPressName") String delPressName){

        return newsAdController.deletePress(delPressName);
    }

    @RequestMapping(value="/allPress", method=RequestMethod.GET)
    public @ResponseBody List<String> getPressNamePAGE(){
        return getAllPressName();
    }

    private List<String> getAllPressName(){
        return newsAdController.searchAllPresses();
    }

    private List<String> getAllDistrictName(){
        return newsAdController.searchAllDistricts();
    }

}