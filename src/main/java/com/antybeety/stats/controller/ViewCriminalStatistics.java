package com.antybeety.stats.controller;

import com.antybeety.stats.model.vo.CrimeRankedVO;
import com.antybeety.stats.model.vo.CrimeStatsVO;
import com.antybeety.stats.service.CrimeStatsService;
import com.antybeety.stats.service.CrimeStatsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Controller
public class ViewCriminalStatistics {
    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public String stats(){
        return "stats/stats";
    }

}
