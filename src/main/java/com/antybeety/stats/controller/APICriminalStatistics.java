package com.antybeety.stats.controller;

import com.antybeety.stats.model.vo.CrimeRankedVO;
import com.antybeety.stats.service.CrimeStatsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class APICriminalStatistics {
    @Autowired
    private CriminalStatistics controller;
    @RequestMapping(value = "/stats",method = RequestMethod.POST)
    public List<List<CrimeRankedVO>> stats(@RequestParam(value = "crimeId") String crimeId){
        if(crimeId==null){
            crimeId="ALL";
        }
        //int year = Calendar.getInstance().get(Calendar.YEAR);
        List<CrimeRankedVO> list = controller.calcRank(2017, crimeId);
        List<CrimeRankedVO> list15 = controller.calcRank(2015, crimeId);
        List<CrimeRankedVO> list16 = controller.calcRank(2016, crimeId);
        List<List<CrimeRankedVO>> listed = new ArrayList<List<CrimeRankedVO>>();
        listed.add(list);
        listed.add(list15);
        listed.add(list16);

        /*
        for(int i=0;i<size;i++) {
            csv = list.get(i).getStats();
            obj.put("total",csv.getName());
            //System.out.println(csv.getDistrict());
            obj.put("area",csv.getDistrict());
            obj.put("year",csv.getYear());
            obj.put("occur",csv.getOccurCnt());
            obj.put("arrest", csv.getArrestCnt());
            obj.put("population",csv.getPopulation());
            obj.put("rank", i+1);
            obj.put("rate", list.get(i).getRate());
            //obj.put("rate", csc.calcRank(2017, crimeId).get(i).getRank());
            jsa.add(obj);
        }
        size = list15.size();
        for(int i=0; i<size; i++) {
            csv = list15.get(i).getStats();
            obj = new JSONObject();
            obj.put("total",csv.getName());
            //	System.out.println(csv.getDistrict());
            obj.put("area",csv.getDistrict());
            obj.put("year",csv.getYear());
            obj.put("occur",csv.getOccurCnt());
            //	System.out.println(csv.getOccurCnt());
            obj.put("arrest", csv.getArrestCnt());
            obj.put("population",csv.getPopulation());
            obj.put("rank", i+1);
            obj.put("rate", list15.get(i).getRate());
            //System.out.println(list15.get(i).getRate());
            //obj.put("rate", csc.calcRank(2017, crimeId).get(i).getRank());
            jsa.add(obj);
        }
        size= list16.size();
        for(int i=0;i<size;i++) {
            csv = list16.get(i).getStats();
            obj = new JSONObject();
            obj.put("total",csv.getName());
            obj.put("area",csv.getDistrict());
            obj.put("year",csv.getYear());
            obj.put("occur",csv.getOccurCnt());
            obj.put("arrest", csv.getArrestCnt());
            obj.put("population",csv.getPopulation());
            obj.put("rank", i+1);
            obj.put("rate", list16.get(i).getRate());
            //obj.put("rate", csc.calcRank(2017, crimeId).get(i).getRank());
            jsa.add(obj);
        }
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().print(jsa);
        response.getWriter().flush();
    }

         */
        return listed;
    }


}
