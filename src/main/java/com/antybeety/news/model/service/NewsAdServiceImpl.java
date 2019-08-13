package com.antybeety.news.model.service;

import com.antybeety.district.model.dao.DistrictDAO;
import com.antybeety.news.model.dao.ArticleInfoDAO;
import com.antybeety.news.model.vo.ArticleInfoVO;
import com.antybeety.press.model.dao.PressDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class NewsAdServiceImpl implements NewsAdService{
    @Autowired
    private ArticleInfoDAO articleDao;
    @Autowired
    private PressDAO pressDao;
    @Autowired
    private DistrictDAO districtDao;


    @Override
    public int addArticle(ArticleInfoVO article) {
        /*
        만들어야할것: code, pressName, districtName, keywordName
        그대로써야핟것: title, summary, url, imgURL,
        sql에서 자동추가: articleTime, viewCnt
         */
        article.setCode(makeArticleCode()); //일련번호 만들어서 반환
        String pressCode = pressDao.searchPressCode(article.getPressName());
        String districtCode = districtDao.searchDistrictCodeByName(article.getDistrictName());

        article.setPressName(pressCode);    //이름을 코드로 변환
        article.setDistrictName(districtCode);  //이름을 코드로 변환
        articleDao.addArticle(article); // db에 article_tb, img_tb에 기사 추가
        return 0;
    }

    public String makeArticleCode(){
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        String res="";
        String now = format.format(new Date()); //오늘 날짜정보

        String lastArtiCode= articleDao.searchLastDate();

        String lastArtiDate= lastArtiCode.substring(0,6);//

        if(lastArtiDate.equals(now)){   //오늘 추가된 기사가 있을 경우 오늘 추가된 기사의 코드 인덱스 +1
            String lastArtiIndex= lastArtiCode.substring(6);
            res= now+String.format("%04d", Integer.parseInt(lastArtiIndex)+1);
        }else{
            res= now+"0001";
        }
        return res;
    }
}
