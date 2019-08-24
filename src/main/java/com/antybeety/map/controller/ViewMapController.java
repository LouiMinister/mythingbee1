package com.antybeety.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewMapController {

    @RequestMapping(value="/map",method = RequestMethod.GET)
    public String showMap(){

        return "map/map";
    }

    @RequestMapping(value="/adminMap",method = RequestMethod.GET)
    public String  showAdminMap() {

        return "admin/adminMap";
    }

}