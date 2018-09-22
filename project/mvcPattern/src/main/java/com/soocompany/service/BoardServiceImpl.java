package com.soocompany.service;

import java.util.List;

import com.soocompany.application.board.dao.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soocompany.application.board.model.BoardVO;

@Service
public class BoardServiceImpl {

//	@Autowired
//	private BoardDAO dao;
//
//	public List<BoardVO> selectBoard() {
//		return dao.getTitle();
//	}
//
//	public List<BoardVO> selectBoardList() {
//		return dao.readBoard();
//	}
//
//	public BoardVO boardDetail(int id) {
//		return dao.boardDetail(id);
//	}
//
//	public BoardVO writeBoard(BoardVO boardVO) {
//		dao.writeBoard(boardVO);
//		return boardVO;
//	}
//
//	public BoardVO editBoard(Integer id, BoardVO boardVO) {
//		dao.editBoard(id, boardVO);
//		return boardVO;
//	}
//
//	public void deleteBoard(int id) {
//		dao.deleteBoard(id);
//	}
}
