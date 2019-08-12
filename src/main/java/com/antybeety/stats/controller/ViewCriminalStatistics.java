package com.antybeety.stats.controller;

import com.antybeety.stats.model.vo.CrimeRankedVO;
import com.antybeety.stats.model.vo.CrimeStatsVO;
import com.antybeety.stats.service.CrimeStatsService;
import com.antybeety.stats.service.CrimeStatsServiceImpl;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class ViewCriminalStatistics {
    private Device currentDevice;
    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public String stats(HttpServletRequest request){

        currentDevice = DeviceUtils.getCurrentDevice(request);


        if(currentDevice.isMobile())
        {
            System.out.println("mobile");
            return "stats/mstats";
        }else if (currentDevice.isTablet()){
            System.out.println("isTablet");
            return "stats/mstats";
        }
        else{
            System.out.println("normal");
            return "stats/mstats";
        }
    }

}
