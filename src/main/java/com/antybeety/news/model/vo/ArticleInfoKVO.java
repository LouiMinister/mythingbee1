package com.antybeety.news.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArticleInfoKVO{
    private String code;
    private String title;
    private String pressName;
    private String summary;
    private String articleTime;
    private int viewCnt;
    private String url;
    private String imgURL;
    private String districtName;
    private List<KeywordVO> keywords;

    public ArticleInfoKVO(){

    }

    public ArticleInfoKVO(String code, String title, String pressName, String summary, String articleTime, int viewCnt, String url, String imgURL, String districtName, List<KeywordVO> keywords) {
        this.code = code;
        this.title = title;
        this.pressName = pressName;
        this.summary = summary;
        this.articleTime = articleTime;
        this.viewCnt = viewCnt;
        this.url = url;
        this.imgURL = imgURL;
        this.districtName = districtName;
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "ArticleInfoKVO{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", pressName='" + pressName + '\'' +
                ", summary='" + summary + '\'' +
                ", articleTime='" + articleTime + '\'' +
                ", viewCnt=" + viewCnt +
                ", url='" + url + '\'' +
                ", imgURL='" + imgURL + '\'' +
                ", districtName='" + districtName + '\'' +
                ", keywords=" + keywords +
                '}';
    }
}
