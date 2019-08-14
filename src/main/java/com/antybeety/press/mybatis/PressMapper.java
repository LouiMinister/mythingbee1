package com.antybeety.press.mybatis;

import com.antybeety.press.model.vo.PressVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PressMapper {
    /*신문사 정보를 추가*/
    int insertPressInfo(HashMap<String, Object> param);

    /*신문사 코드를 통해 신문사 이름을 반환*/
    String searchPressName(String code);

    /*신문사 이름을 통해 신문사 코드를 반환*/
    String searchPressCode(String name);

    /*모든 신문사 이름을 반환*/
    List<String> searchAllNames();

    int deletePressByName(String name);

    int deletePressByCode(String code);

    int updatePressName(HashMap<String, Object> param);
}
