package com.soocompany.application.board.dao;

import com.soocompany.application.board.model.BoardVO;
import com.sun.tools.corba.se.idl.IncludeGen;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAO {

    @Autowired
    private SqlSession sqlSession;

    public BoardVO selectBoard(Integer id) {
        return sqlSession.selectOne("board.select");
    }

    public List<BoardVO> selectBoards() {
        return sqlSession.selectList("board.selectAll");
    }

    public Integer insertBoard(BoardVO board) {
        return sqlSession.insert("board.insert", board);
    }

    public Integer updateBoard(BoardVO board) {
        return sqlSession.update("board.update", board);
    }

    public Integer deleteBoard(Integer id) {
        return sqlSession.update("board.delete", id);
    }

//    List<BoardVO> getTitle();
//    List<BoardVO> readBoard();
//    BoardVO boardDetail(int id);
//
//    void writeBoard(BoardVO boardVO);
//    void editBoard(Integer id, BoardVO boardVO);
//    void deleteBoard(int id);

//    @Repository
//    public class BoardDAOImpl implements com.soocompany.dao.BoardDAO {
//        private static final String NAMESPACE = "com.soocompany.mvc.BoardMapper";
//
//        @Autowired
//        private SqlSession sqlSession;
//
//        @Override
//        public List<BoardVO> getTitle() {
//            return sqlSession.selectList(NAMESPACE +".selectBoard");
//        }
//
//        public List<BoardVO> readBoard() {
//            return sqlSession.selectList(NAMESPACE + ".readBoard");
//        }
//
//        public BoardVO editBoard() {
//            return sqlSession.selectOne(NAMESPACE + ".editBoard");
//        }
//
//        public BoardVO boardDetail(int id) {
//            return sqlSession.selectOne(NAMESPACE + ".boardDetail", id);
//        }
//
//        @Override
//        public void writeBoard(BoardVO boardVO) {
//            sqlSession.insert(NAMESPACE + ".writeBoard", boardVO);
//        }
//
//        @Override
//        public void editBoard(Integer id, BoardVO boardVO) {
//            boardVO.setIdx(id);
//            sqlSession.update(NAMESPACE + ".editBoard", boardVO);
//        }
//
//        @Override
//        public void deleteBoard(int id) {
//            sqlSession.delete(NAMESPACE + ".deleteBoard", id);
//        }
//
//
//    }
}
