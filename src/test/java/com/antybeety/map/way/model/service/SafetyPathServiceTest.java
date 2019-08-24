package com.antybeety.map.way.model.service;


import com.antybeety.map.way.model.vo.NodeVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml"
})
public class SafetyPathServiceTest {

     @Autowired
     SafetyPathService service;

    @Test
    public void test_좌표로부터_가장가까운_노드찾기(){
        NodeVO node = service.matchNode(37.48669756159295,126.95819580920832);
        System.out.println(node);
    }
}
