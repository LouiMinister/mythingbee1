package com.antybeety.stats.model.dao;

import com.antybeety.stats.model.vo.CrimeStatsVO;

import java.util.List;

public interface CrimeStatsDAO {
    List<CrimeStatsVO> searchStatsListByYear(int year);
    List<CrimeStatsVO> searchStatsListByYearCategory(int year, String category);
}
