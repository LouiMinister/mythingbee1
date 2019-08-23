package com.antybeety.map.mybatis;

import com.antybeety.map.model.vo.*;

import java.util.List;
import java.util.Map;

public interface MapMapper {
//    맵에 시설물 표시
    List<FacilityMarkVO> selectBell(Map<String, Object> map);
    List<FacilityMarkVO> selectPolice(Map<String, Object> map);
    List<FacilityMarkVO> selectConvenience(Map<String, Object> map);
    List<FacilityMarkVO> selectCCTV(Map<String, Object> map);
    List<FacilityMarkVO> selectLight(Map<String, Object> map);

//    선택한 시설물 상셍정보 표시
    FacilityDetailVO getBellDetail(String code);
    FacilityDetailVO getPoliceDetail(String code);
    FacilityDetailVO getConvenienceDetail(String code);
    FacilityDetailVO getCCTVDetail(String code);
    FacilityDetailVO getLightDetail(String code);

    List<RoadVO> getAllRoad();
    List<NodeData> getAllNode();
    List<Edge> getAllEdge();

    void addNode(Map<String, Object> node);
    void addEdge(Map<String, Object> edge);
}
