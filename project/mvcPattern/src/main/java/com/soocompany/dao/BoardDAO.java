package com.soocompany.dao;

import com.soocompany.dto.BoardVO;

import java.util.List;

public interface BoardDAO {
	List<BoardVO> getTitle(); 
	List<BoardVO> readBoard();
	//BoardVO editBoard();
	BoardVO boardDetail(int id);

	void writeBoard(BoardVO boardVO);

    void editBoard(Integer id, BoardVO boardVO);

    void deleteBoard(int id);
}
