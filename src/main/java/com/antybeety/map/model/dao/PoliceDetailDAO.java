package com.antybeety.map.model.dao;

        import com.antybeety.map.model.vo.FacilityDetailVO;
        import com.antybeety.map.mybatis.MapMapper;
        import org.apache.ibatis.session.SqlSession;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Repository;

@Repository
public class PoliceDetailDAO extends  FacilityDetailDAOImpl{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public FacilityDetailVO searchDetail(String code) {
        MapMapper mapper = sqlSession.getMapper(MapMapper.class);
        return mapper.getPoliceDetail(code);
    }

    @Override
    public String getFacilName() {
        return "PD";
    }
}
