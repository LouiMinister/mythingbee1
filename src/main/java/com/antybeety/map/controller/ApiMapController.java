package com.antybeety.map.controller;

import com.antybeety.map.model.vo.FacilityDetailVO;
import com.antybeety.map.model.vo.FacilityMarkVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.parser.JSONParser;
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

    @RequestMapping(value = "/search", method = RequestMethod.GET)
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
        catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping(value="/search/mobile", method = RequestMethod.GET)
    public List<Map<String,Object>> showFacilityByMobile(@RequestParam Map<String,Object> request) {// post방식으로 할때는 Requestbody


        Map<String,Object> bounds = new HashMap<>();
        bounds.put("la",request.get("la"));
        bounds.put("ka",request.get("ka"));
        bounds.put("ea",request.get("ea"));
        bounds.put("ja",request.get("ja"));

        List<Integer> facilFlag = new ArrayList<>();
        List<String> facilName = new ArrayList<>();

        List<Map<String,Object>> result = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            facilFlag = mapper.readValue((String)request.get("facilFlag"),List.class);
            facilName = mapper.readValue((String)request.get("facilName"),List.class);

//            facilName = (List<String>) temp.get("facilName");

            result = fc.searchFacility(bounds,facilFlag,facilName);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }


        return result;
    }

    @RequestMapping(value="/search/around", method = RequestMethod.GET)
    public List<FacilityMarkVO> searchAroundFacility(@RequestParam double la, // bottom
                                                     double ka, // top
                                                     double ea, // left
                                                     double ja){    // top

        Map<String,Object> bounds = new HashMap<>();
        bounds.put("la",la);
        bounds.put("ka",ka);
        bounds.put("ea",ea);
        bounds.put("ja",ja);
        List<FacilityMarkVO> result = fc.searchAroundFacility(bounds);

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
            result.put("code",code);
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

    @RequestMapping(value="/distanceAll", method = RequestMethod.GET)
    public List<Integer> calcDistanceAll(@RequestParam String locationList){

        List<Map<String,Object>> result = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.readValue(locationList,List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }



        return fc.searchDistanceAll(result);
    }

    @RequestMapping(value="/distance",method = RequestMethod.GET)
    public int calcDistance(@RequestParam double startLat, double startLon, double endLat, double endLon){
        return fc.searchDistance(startLat,startLon,endLat,endLon);
    }
}