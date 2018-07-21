package com.soocompany.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.soocompany.dto.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	private static final String NAMESPACE = "com.soocompany.mvc.BoardMapper";
	
	@Autowired
	private SqlSession sqlSession;
	    
    @Override
    public List<BoardVO> getTitle() {
        return sqlSession.selectList(NAMESPACE +".selectBoard");
    }
    
    public List<BoardVO> readBoard() {
    	return sqlSession.selectList(NAMESPACE + ".readBoard");
    }
    
    public BoardVO editBoard() {
    	return sqlSession.selectOne(NAMESPACE + ".editBoard");
    }
    
    public BoardVO boardDetail(int id) {
    	return sqlSession.selectOne(NAMESPACE + ".boardDetail", id);
    }

    @Override
    public void writeBoard(BoardVO boardVO) {
        sqlSession.insert(NAMESPACE + ".writeBoard", boardVO);
    }

    @Override
    public void editBoard(Integer id, BoardVO boardVO) {
        boardVO.setIdx(id);
        sqlSession.update(NAMESPACE + ".editBoard", boardVO);
    }

    @Override
    public void deleteBoard(int id) {
        sqlSession.delete(NAMESPACE + ".deleteBoard", id);
    }


}
