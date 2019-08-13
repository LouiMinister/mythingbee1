package com.antybeety.news.model.dao;

import com.antybeety.news.model.vo.ArticleInfoKVO;
import com.antybeety.news.model.vo.KeywordVO;
import com.antybeety.news.mybatis.KeywordMapper;
import com.antybeety.news.mybatis.NewsMapper;
import com.mysql.cj.xdevapi.SqlDataResult;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class KeywordDAO {

    @Autowired
    private SqlSession sqlSession;

    private KeywordMapper getMapper(){
        return sqlSession.getMapper(KeywordMapper.class);
    }

    /*키워드가 연결된 기사의 코드와 키워드 코드, 키워드 명을 받아서 DB에 추가*/
    public int addKeyword(String code, KeywordVO keyword){
        KeywordMapper mapper = getMapper();
        String preCodeByName="";
        String preCode="";

        //같은 이름의 키워드가 존재하면 키워드를 추가하지 않고 연결만 하면됨.
        //같은 코드를 가진 키워드가 존재하면 코드를 통해 기사를 찾아서 가장 마지막 인덱스의 코드를 가져옴.
            //이를 통해서 코드를 다시 만들어 기사에 추가함
        //같은 이름, 코드를 가지고 있지않으면 통과

        preCodeByName= searchCodeByName(keyword.getName()); //같은 이름의 키워드가 존재하면 코드리턴, 없으면 null 리턴
        preCode= searchCode(keyword.getCode()); //같은 코드를 가진 키워드가 존재하면 해당하는 코드 리턴 없으면 null 리턴

        HashMap<String,String> param = new HashMap<String, String>();

        if(preCodeByName==null && preCode==null){   //둘다 널일경우 -> 문자코드에 01붙혀서 코드 만들어넣기

            keyword.setCode(keyword.getCode()+"01");
            param.put("ke_code",keyword.getCode());
            param.put("ar_code",code);

            mapper.addKeyword(keyword);      //키워드테이블에 추가
            mapper.addKeywordPivot(param);  //피벗테이블에 추가
        }
        else if(preCode!=null && preCodeByName==null){     //같은 코드를 가진 키워드가 존재하고 이름은 중복되지 않은경우
            int index=Integer.parseInt(preCode.substring(4));
            String charCode = preCode.substring(0,4);
            String newCode = charCode+String.format("%02d", index+1);

            keyword.setCode(newCode);       //코드를 형식에 맞게 생성
            param.put("ar_code",code);
            param.put("ke_code",keyword.getCode());

            mapper.addKeyword(keyword);
            mapper.addKeywordPivot(param);
        }else {    //같은 이름의 키워드가 존재하는 경우
                    //현재 존재하는 같은 이름을 가진 키워드의 코드를 통해 연결

            

            param.put("ar_code",code);
            param.put("ke_code",preCodeByName);
            mapper.addKeywordPivot(param);  //피벗에만 추가
        }
        return 1;

    }

    /*그 코드를 포함한 마지막 기사의 코드 찾기*/
    public String searchCode(String code){
        KeywordMapper mapper = getMapper();

        return mapper.searchCode(code);
    }

    /*키워드 이름을 통해 코드 찾기. 이름이 존재하다면 해당하는 키워드의 코드를 리턴. 없다면 ""을 리턴*/
    public String searchCodeByName(String name){
        KeywordMapper mapper = getMapper();
        return mapper.searchCodeByName(name);

    }
}
