package com.antybeety.stats.mybatis;

import com.antybeety.stats.model.vo.CrimeStatsVO;

import java.util.List;
import java.util.Map;

public interface StatsMapper {
    List<CrimeStatsVO> searchStatsByAllCrime(int year);
    List<CrimeStatsVO> searchStatsByOneCrime(Map<String, Object> map);

}

