package com.antybeety.press.model.service;

import com.antybeety.press.model.dao.PressDAO;
import com.antybeety.press.model.vo.PressVO;
import com.antybeety.press.mybatis.PressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PressService {
    @Autowired
    private PressDAO dao;

    public String searchPressCode(String name) {
        return dao.searchPressCode(name);
    }

    public String searchPressName(String code) {
        return dao.searchPressName(code);
    }

    public List<String> searchAllNames() {
        return dao.searchAllNames();
    }

    public int insertPress(PressVO press) {
        return dao.insertPressInfo(press);
    }

    public int deleteByName(String name) {
        return dao.deleteByName(name);
    }

    public int deleteByCode(String code) {
        return dao.deleteByCode(code);
    }

    public int updatePressName(String code, String name) {
        return dao.updatePressName(code, name);
    }


}
