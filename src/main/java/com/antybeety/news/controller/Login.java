package com.antybeety.news.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Log4j
@Controller
public class Login {
    @Autowired
    private NewsAdController newsAdController;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String adminIndex() {
        return "admin/adminLogin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    String adminLogin(@RequestParam(value = "id", required = true) String id,
                      @RequestParam(value = "password", required = true) String password) {
        String pass = "verified";
        String result = "/admin/news";

        if (pass.equals(newsAdController.login(id, password))) {
            return result.trim();
        } else {
            System.out.println("로그인 실패");
            return null;
        }
    }
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "error";
    }
}
