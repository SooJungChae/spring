package com.soocompany.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.soocompany.dto.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	private static final String Namespace = "com.soocompany.mvc.BoardMapper";
	
	@Autowired
	private SqlSession sqlSession;
	    
    @Override
    public List<BoardVO> getTitle() {
        return sqlSession.selectList(Namespace+".selectBoard");
    }
}
