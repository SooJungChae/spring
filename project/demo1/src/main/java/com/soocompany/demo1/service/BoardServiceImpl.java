package com.soocompany.demo1.service;

import com.soocompany.demo1.dao.BoardDAO;
import com.soocompany.demo1.dto.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl {
	@Autowired
	private BoardDAO dao;
	
	public List<BoardVO> selectBoard() {
		return dao.selectBoard();
	}
	
	public List<BoardVO> selectBoardList() {
		return dao.readBoard();
	}
	
	public BoardVO editBoard() {
		return dao.editBoard();
	}
	
	public BoardVO boardDetail(int id) {
		return dao.boardDetail(id);
	}
	
}
