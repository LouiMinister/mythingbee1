package com.antybeety.map.report.model.dao;

import com.antybeety.map.mybatis.ReportMapper;
import com.antybeety.map.report.model.vo.ReportVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ReportDAOImpl implements ReportDAO{
    @Autowired
    private SqlSession sqlSession;
    private ReportMapper getMapper(){
        return sqlSession.getMapper(ReportMapper.class);
    }
    public ReportDAOImpl(){

    }
    @Override
    public int addReport(ReportVO report) {
        ReportMapper mapper =getMapper();
        return mapper.addReport(report);
    }
    @Override
    public List<ReportVO> searchReport(Map<String, Object> bounds) {
        ReportMapper mapper = getMapper();
        List<ReportVO> list = mapper.searchReport(bounds);
        return list;
    }
}
