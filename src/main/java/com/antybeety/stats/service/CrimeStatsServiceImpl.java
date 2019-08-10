package com.antybeety.stats.service;

import com.antybeety.stats.model.dao.CrimeStatsDAO;
import com.antybeety.stats.model.vo.CrimeRankedVO;
import com.antybeety.stats.model.vo.CrimeStatsVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CrimeStatsServiceImpl implements CrimeStatsService {
    @Autowired
    private CrimeStatsDAO dao;

    public List<CrimeRankedVO> calcRank(int year, String category) {
        switch(category) {
            case "MU" :
                category="살인";
                break;
            case "RO" :
                category="강도";
                break;
            case "SE" :
                category="성범죄";
                break;
            case "TH" :
                category="절도";
                break;
            case "VI" :
                category="폭력";
                break;
            default:
                category="ALL";
        }
        CrimeStatsVO csvo = new CrimeStatsVO();// 범죄 통계 1개
        List<CrimeRankedVO> crvo = new ArrayList<CrimeRankedVO>();// 범죄통계 + 랭크 리스트
        List<CrimeStatsVO> csvoList = new ArrayList<CrimeStatsVO>();// 범죄통계 리스트

        if(category.equals("ALL")) {
            csvoList = dao.searchStatsListByYear(year);
            for (int i = 0; i < csvoList.size(); i++) {
                csvoList.get(i).setName(category);
            }
        }
        else {
        csvoList = dao.searchStatsListByYearCategory(year, category);// year와 category 로 범죄 리스트 DB에서 불러옴
    }
    crvo=calculation(csvoList);
        return crvo;
    }

    public List<CrimeRankedVO> calculation(List<CrimeStatsVO> ranked) {
        final double standard = 10000;
        List<CrimeRankedVO> basket = new ArrayList<CrimeRankedVO>();

        for(int i=0;i<ranked.size();i++) {
            int population=ranked.get(i).getPopulation();
            int occurCnt=ranked.get(i).getOccurCnt();
            double rate=occurCnt*10000/population;
            CrimeRankedVO cr = new CrimeRankedVO(ranked.get(i),rate);
            basket.add(cr);
        }
        basket.sort(Comparator.reverseOrder());
        for(int i=0;i<basket.size();i++){
            basket.get(i).setRank(i+1);
        }

        return basket;
    }
   }

