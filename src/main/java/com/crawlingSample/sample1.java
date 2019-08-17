package com.crawlingSample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class sample1 {
    public static void main(String[] args) throws Exception{

        String []articleURL= {"https://news.v.daum.net/v/20190815090039789",
        "https://news.v.daum.net/v/20190814212855807",
                "https://news.v.daum.net/v/20190815090643916",
                "https://news.v.daum.net/v/20190812200137883"
        };   //뉴스기사 URL
        Document doc =null;
        for(String s: articleURL) {
            doc = Jsoup.connect(s).get();     // document 객체 생성


            String title = doc.select("h3.tit_view").text();
            String summary = doc.select("strong.summary_view").text();
            String summary2 = doc.select("div.layer_summary > *:not(.ico_info_summary)").text();
            String summary3= doc.select("p[dmcf-ptype=general]").text();
            String summary4="";
            if(doc.select("p[dmcf-ptype=general]").first()!=null){
                summary4= doc.select("p[dmcf-ptype=general]").first().text();
            }
            String imgUrl = doc.select("img.thumb_g_article").attr("src");
            String press = doc.select("meta[name=article:media_name]").attr("content");
            //press=doc.getElementsByTag("meta").attr("content");

            // String summary3 = doc.getElementsByTag("div.layer_summary").attr("src");
            System.out.println("TRUEFALSE"+summary.equals(""));
            System.out.println("제목:"+title);
            System.out.println("요약"+summary);
            System.out.println("요약2"+summary2);
            System.out.println("요약3"+summary3);
            System.out.println("요약4"+summary4);
            System.out.println("이미지"+imgUrl);
            System.out.println("기사"+press);
            System.out.println("------------------------------------------------------------------");
        }
    }
}
