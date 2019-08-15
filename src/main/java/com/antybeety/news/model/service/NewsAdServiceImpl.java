package com.antybeety.news.model.service;

import com.antybeety.district.model.dao.DistrictDAO;
import com.antybeety.news.model.dao.ArticleInfoDAO;
import com.antybeety.news.model.dao.KeywordDAO;
import com.antybeety.news.model.vo.ArticleInfoKVO;
import com.antybeety.news.model.vo.ArticleInfoVO;
import com.antybeety.news.model.vo.KeywordVO;
import com.antybeety.press.model.dao.PressDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NewsAdServiceImpl implements NewsAdService {
    @Autowired
    private ArticleInfoDAO articleDao;
    @Autowired
    private PressDAO pressDao;
    @Autowired
    private DistrictDAO districtDao;
    @Autowired
    private KeywordDAO keywordDao;


    private ArticleInfoVO parseKvoToVo(ArticleInfoKVO article) {
        ArticleInfoVO vo = new ArticleInfoVO();
        vo.setCode(article.getCode());
        vo.setTitle(article.getTitle());
        vo.setImgURL(article.getImgURL());
        vo.setUrl(article.getUrl());
        vo.setSummary(article.getSummary());
        vo.setDistrictName(article.getDistrictName());
        vo.setPressName(article.getPressName());
        vo.setArticleTime(article.getArticleTime());
        vo.setViewCnt(article.getViewCnt());

        //키워드 형식 파싱. KeywordVo-> "키워드1,키워드2,키워드3"
        List<KeywordVO> kws = article.getKeywords();
        String keywordArray = "";
        for (KeywordVO k : kws) {
            keywordArray += k.getName() + ",";
        }
        //마지막 쉼표 제거
        keywordArray = keywordArray.substring(0, keywordArray.length() - 1);

        vo.setKeywordName(keywordArray);

        return vo;
    }


    @Override
    public int addArticle(ArticleInfoKVO article) {
        /*
        만들어야할것: code, pressName, districtName, keywords
        그대로써야핟것: title, summary, url, imgURL,
        sql에서 자동추가: articleTime, viewCnt
         */
        article.setCode(makeArticleCode()); //일련번호 만들어서 반환
        String pressCode = pressDao.searchPressCode(article.getPressName());
        String districtCode = districtDao.searchDistrictCodeByName(article.getDistrictName());

        article.setPressName(pressCode);    //이름을 코드로 변환
        article.setDistrictName(districtCode);  //이름을 코드로 변환
        articleDao.addArticle(parseKvoToVo(article)); // db에 article_tb, img_tb에 기사 추가

        List<KeywordVO> keywords = article.getKeywords();

        int res=0;
        res = addKeywordAtArticle(article.getCode(),keywords);  //기사에 키워드 추가

        return 0;
    }

    public int addKeywordAtArticle(String articleCode, List<KeywordVO> keywords){
        int res=0;
        for(KeywordVO k : keywords){
            res += addKeywordWithArticle(articleCode, k);
            //추가할 때 리턴받는 값을 통해 실패여부 리턴 (추후 추가)

        }
        return 0;
    }

    public int addKeywordWithArticle(String code, KeywordVO keyword) {
        return keywordDao.addKeyword(code, keyword);
    }

    public String makeArticleCode() {
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        String res = "";
        String now = format.format(new Date()); //오늘 날짜정보

        String lastArtiCode = articleDao.searchLastDate();

        String lastArtiDate = lastArtiCode.substring(0, 6);//

        if (lastArtiDate.equals(now)) {   //오늘 추가된 기사가 있을 경우 오늘 추가된 기사의 코드 인덱스 +1
            String lastArtiIndex = lastArtiCode.substring(6);
            res = now + String.format("%04d", Integer.parseInt(lastArtiIndex) + 1);
        } else {
            res = now + "0001";
        }
        return res;
    }

    @Override
    public List<ArticleInfoVO> searchAllArticles() {
        return articleDao.searchAllArticles();
    }

    @Override
    public String login(String id, String password) {
        if((id.equals("admin"))&&(password.equals("abty"))) {
            return "verified";
        }
        else{
            return "failed";
        }
    }

    @Override
    public ArticleInfoVO searchArticle(String article) {
        return articleDao.searchArticleInfo(article);
    }


    @Override
    public int deleteArticles(List<String> code) {

        for(String c : code){
            articleDao.removeArticle(c);
        }
        return 0;
    }


    @Override
    public List<String> searchAllDistricts() {
        return districtDao.searchAllDistrictNames();
    }

    @Override
    public int updateArticle(ArticleInfoKVO article) {

        String pressCode= pressDao.searchPressCode(article.getPressName());
        String districtCode = districtDao.searchDistrictCodeByName(article.getDistrictName());

        article.setPressName(pressCode);    //이름을 코드로 변환
        article.setDistrictName(districtCode);  //이름을 코드로 변환
       int result = articleDao.updateArticle(article);

        List<KeywordVO> keywords = article.getKeywords();
        int res=0;
        //키워드와 기사 사이 연결을 전부 삭제
        res = keywordDao.cutAllKeywordBtwArticle(article.getCode());

        res = addKeywordAtArticle(article.getCode(),keywords);  //기사에 키워드 추가

        return result;
    }
}
