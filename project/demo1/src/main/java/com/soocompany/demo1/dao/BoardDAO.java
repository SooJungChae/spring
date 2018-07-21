package com.soocompany.demo1.dao;


import com.soocompany.demo1.dto.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BoardDAO {
	List<BoardVO> selectBoard();
	List<BoardVO> readBoard();
	BoardVO editBoard();
	BoardVO boardDetail(int id);
}
