package com.antybeety.map.way.controller;

import com.antybeety.map.way.model.service.MapSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MapSettingController {
    @Autowired
    private MapSettingService mapSettingService;

    public void setAllSafetyValue() {
        mapSettingService.setAllSafetyValue();
    }

    public void setHeuristic() {
        mapSettingService.setHeuristicValue();
    }
}
