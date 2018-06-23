package com.soocompany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soocompany.dao.BoardDAO;
import com.soocompany.dto.BoardVO;

@Service
public class BoardServiceImpl {
	@Autowired
	private BoardDAO dao;
	
	public List<BoardVO> selectBoard() {
		return dao.getTitle();
	}
	
}
