package com.antybeety.map.way.model.dao;

import com.antybeety.map.way.model.vo.NodeVO;
import com.antybeety.news.model.dao.ArticleInfoDAO;
import com.antybeety.news.model.vo.ArticleInfoKVO;
import com.antybeety.news.model.vo.ArticleInfoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml"
})
public class NodeDaoTest {

    @Autowired
    private NodeDAO dao;

    @Test
    public void test_영역으로_노드찾기(){
        List<NodeVO> nodes = dao.searchNodesByArea(37.47686563082442, 126.95416760502995,
                37.478168073615855, 126.95543304469112);
        System.out.println(nodes);
    }

    @Test
    public void test_아이디로_노드찾기(){
        NodeVO node= dao.searchNodeById("2420067002");
        System.out.println(node);
    }
}
