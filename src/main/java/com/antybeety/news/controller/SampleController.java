package com.antybeety.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class SampleController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/index2", method = RequestMethod.GET)
    public String index2() {
        return "news/news";
    }

/*    @RequestMapping(value = "/news/getArticles.do", method = RequestMethod.GET)
    public String index23() {
        System.out.println("낑낑");
        return "news/news";
    }*/
}
