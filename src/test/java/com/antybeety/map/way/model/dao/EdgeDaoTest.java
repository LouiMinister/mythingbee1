package com.antybeety.map.way.model.dao;

import com.antybeety.map.way.model.vo.EdgeVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml"
})
public class EdgeDaoTest {

    @Autowired
    private EdgeDAO dao;

    @Test
    public void test_영역으로_엣지찾기(){
        List<EdgeVO> nodes = dao.searchEdgesByArea(37.47686563082442, 126.95416760502995,
                37.478168073615855, 126.95543304469112);
        System.out.println(nodes);
    }

    @Test
    public void test_아이디로_엣지찾기(){
        EdgeVO node= dao.searchEdgeById("2420192102");
        System.out.println(node);
    }
}
