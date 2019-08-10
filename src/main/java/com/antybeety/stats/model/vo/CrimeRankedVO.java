package com.antybeety.stats.model.vo;

import lombok.Data;

@Data
public class CrimeRankedVO implements  Comparable<CrimeRankedVO>{
    private CrimeStatsVO stats;
    private double rate;
    private int rank;

    public CrimeRankedVO() {}
    public CrimeRankedVO(CrimeStatsVO stats, double rate) {
        this.stats=stats;
        this.rate = rate;
    }
    public CrimeRankedVO(CrimeStatsVO stats, double rate, int rank){
        this.stats=stats;
        this.rate=rate;
        this.rank=rank;
    }
    public String toString() {
        return "CrimeRankedVO [stats=" + stats + ", rate=" + rate +", rank=" +rank+"]";
    }
    public int compareTo(CrimeRankedVO arg0) {
        int targetRank = arg0.getStats().getOccurCnt();
        if(stats.getOccurCnt()==targetRank) return 0;
        else if(stats.getOccurCnt() > targetRank) return 1;
        else return -1;
    }

}
