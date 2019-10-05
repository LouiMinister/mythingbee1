package com.antybeety.map.report.controller;

import com.antybeety.map.report.model.service.ReportService;
import com.antybeety.map.report.model.vo.ReportVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class ApiReportController{

    @Autowired
    private ReportController controller;

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public int addReport(@RequestParam Map<String,Object> map){
        double lat = Double.parseDouble((String) map.get("lat"));
        double lon =  Double.parseDouble((String) map.get("lon"));
        String address = (String)map.get("address");
        String category = (String)map.get("category");
        String date = (String)map.get("date");
        String comments = (String)map.get("comments");

        ReportVO rv = new ReportVO(lat,lon,address,category,date,comments);
        controller.addReport(rv);
        int result=0;
        return result;
    }

    @RequestMapping(value="/search",method=RequestMethod.GET)
    public List<ReportVO> searchReport(@RequestParam Map<String,Object> request){
        Map<String,Object> bounds = new HashMap<String,Object>();


        bounds.put("rbLat",request.get("rbLat"));
        bounds.put("rbLon",request.get("rbLon"));
        bounds.put("ltLat",request.get("ltLat"));
        bounds.put("ltLon",request.get("ltLon"));
        System.out.println(bounds.get("rbLat"));
        System.out.println(bounds.get("rbLon"));
        System.out.println(bounds.get("ltLat"));
        System.out.println(bounds.get("ltLon"));

        List<ReportVO> result;
        result = controller.searchReport(bounds);
        System.out.println(result.size());
        return result;
    }
}
