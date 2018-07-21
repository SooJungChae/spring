package com.soocompany.demo1.dao;


import com.soocompany.demo1.dto.BoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public class BoardDAOImpl { //implements BoardDAO {
//	private static final String Namespace = "com.soocompany.mvc.BoardMapper";
//
//	@Autowired
//	private SqlSession sqlSession;
//
//    @Override
//    public List<BoardVO> getTitle() {
//        return sqlSession.selectList(Namespace+".selectBoard");
//    }
//
//    public List<BoardVO> readBoard() {
//    	return sqlSession.selectList(Namespace+ ".readBoard");
//    }
//
//    public BoardVO editBoard() {
//    	return sqlSession.selectOne(Namespace+ ".editBoard");
//    }
//
//    public BoardVO boardDetail(int id) {
//    	return sqlSession.selectOne(Namespace+ ".boardDetail", id);
//    }
}
