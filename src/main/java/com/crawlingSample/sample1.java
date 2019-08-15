package com.crawlingSample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class sample1 {
    public static void main(String[] args) throws Exception{
        String articleURL= "https://news.v.daum.net/v/20190815090039789";   //뉴스기사 URL
        Document doc = Jsoup.connect(articleURL).get();     // document 객체 생성
        Elements ele = null;

        String title = doc.select("h3.tit_view").text();
        String summary = doc.select("strong.summary_view").text();
        String summary2 = doc.select("div.layer_summary").text();
        String imgUrl = doc.select("img.thumb_g_article").attr("src");


       // String summary3 = doc.getElementsByTag("div.layer_summary").attr("src");


        System.out.println(title);
        System.out.println(summary);
        System.out.println(summary2);
        System.out.println(imgUrl);
    }
}
