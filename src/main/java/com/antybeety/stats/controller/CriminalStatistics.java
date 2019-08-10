package com.antybeety.stats.controller;

import com.antybeety.stats.model.vo.CrimeRankedVO;
import com.antybeety.stats.service.CrimeStatsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class CriminalStatistics {
    @Autowired
    private CrimeStatsService service;

    List<CrimeRankedVO> calcRank(int year, String crimeId){
        return service.calcRank(year,crimeId);
    }
}
