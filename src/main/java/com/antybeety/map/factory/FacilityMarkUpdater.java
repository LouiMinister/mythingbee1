package com.antybeety.map.factory;

import com.antybeety.map.model.vo.FacilityMarkVO;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FacilityMarkUpdater  implements InitializingBean, DisposableBean {

    @Autowired
    private Map<String, FacilityMarkReloader> reloaderMap;

    @Autowired
    private FacilityMarkHolder holder;

    public List<FacilityMarkVO> update(Map<String,Object> bounds, String beanName) {
        List<FacilityMarkVO> temp = null;
        try{
//            this.reloaderMap.forEach((k,v) ->
//                    v.searchFacilities(bounds, this.holder.get(beanName));
            temp = new ArrayList<>();
            for(String key: reloaderMap.keySet()){
               temp.addAll(reloaderMap.get(key).searchFacilities(bounds,this.holder.get(beanName)));
            }
        }
        catch(Exception e){

        }
        finally {
            return temp;
        }
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("FacilityMarkUpdater의 destory");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("FacilityMarkUpdater의 afterPropertiesSet");
    }
}
