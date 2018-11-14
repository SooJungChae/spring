package com.soocompany.application.board.service;

import com.soocompany.application.board.dao.BoardDAO;
import com.soocompany.application.board.model.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardDAO dao;

    public BoardVO selectBoard(Integer id) {
        return dao.selectBoard(id);
    }

    public List<BoardVO> selectBoards() {
        List<BoardVO> result = dao.selectBoards();
        return result;
    }

    public Integer insertBoard(BoardVO board) {
        return dao.insertBoard(board);
    }

    public Integer updateBoard(BoardVO board) {
        return dao.updateBoard(board);
    }

    public Integer deleteBoard(Integer id) {
        return dao.deleteBoard(id);
    }
}
