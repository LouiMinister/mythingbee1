package com.antybeety.stats.service;

import com.antybeety.stats.model.vo.CrimeRankedVO;
import com.antybeety.stats.model.vo.CrimeStatsVO;

import java.util.List;

public interface CrimeStatsService {
    List<CrimeRankedVO> calcRank(int year, String category);
    List<CrimeRankedVO> calculation(List<CrimeStatsVO> ranked);
}
