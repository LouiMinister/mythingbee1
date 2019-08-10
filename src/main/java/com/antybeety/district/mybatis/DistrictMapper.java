package com.antybeety.district.mybatis;

import java.util.List;

public interface DistrictMapper {

    List<String> searchAllDistrictNames();
    List<String> searchAllDistrictCodes();
    String searchDistrictNameByCode(String code);
    String searchDistrictCodeByName(String name);
}
