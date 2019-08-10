package com.antybeety.stats.model.dao;


import com.antybeety.stats.mybatis.StatsMapper;
import com.antybeety.stats.model.vo.CrimeStatsVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CrimeStatsDAOImpl implements CrimeStatsDAO {
    @Autowired
    private SqlSession sqlSession;

    public List<CrimeStatsVO> searchStatsListByYear(int year){
        StatsMapper mapper = sqlSession.getMapper(StatsMapper.class);
        List<CrimeStatsVO> list = mapper.searchStatsByAllCrime(year);
        return list;

    }

    public List<CrimeStatsVO> searchStatsListByYearCategory(int year, String category) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("year",year);
        map.put("crime",category);
        StatsMapper mapper  = sqlSession.getMapper(StatsMapper.class);
        List<CrimeStatsVO> list = mapper.searchStatsByOneCrime(map);

        return list;
    }
}
