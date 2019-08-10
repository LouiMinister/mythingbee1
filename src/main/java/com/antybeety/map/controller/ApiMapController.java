package com.antybeety.map.controller;

import com.antybeety.map.model.vo.FacilityDetailVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/map/")
public class ApiMapController {

    @Autowired
    private FacilityController fc;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
//    @PostMapping(value="/search",
//                    consumes = "application/json",
//                    produces = {MediaType.TEXT_PLAIN_VALUE})
    public List<Map<String,Object>> showFacility(@RequestParam Map<String, Object> request) {

        String sJson = null;
        List<Map<String,Object>> result = new ArrayList<>();

        Map<String, Object> bounds = new HashMap<>();
        List<Integer> facilFlag = new ArrayList<>();
        List<String> facilName = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        try{
            bounds = mapper.readValue((String) request.get("bounds"), Map.class);
            facilFlag = mapper.readValue((String)request.get("facilFlag"), List.class);
            facilName = mapper.readValue((String)request.get("facilName"), List.class);
            result = fc.searchFacility(bounds,facilFlag,facilName);

            sJson = mapper.writeValueAsString(result);

        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }

        return result;
    }

    @RequestMapping(value="/detail", method=RequestMethod.GET)
//    @GetMapping(value="/detail",
//                    consumes= "application/json",
//                    produces = {MediaType.TEXT_PLAIN_VALUE})
    public Map<String,String> getDetail(@RequestParam String code) {

        String type = code.substring(0,2);

        Map<String,String> result = new HashMap<>();
        try{
            FacilityDetailVO fd = fc.searchDetail(type,code);
            result.put("roadAddr",fd.getRoadAddr());
            result.put("landAddr",fd.getLandAddr());
            result.put("adminName",fd.getAdminName());
            result.put("adminTel",fd.getAdminTel());
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return result;
    }
}
