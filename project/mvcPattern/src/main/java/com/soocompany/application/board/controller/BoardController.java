package com.soocompany.application.board.controller;

import com.soocompany.application.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public ModelAndView BoardList() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("board/board");
        mv.addObject("boards", boardService.selectBoards());

        return mv;
    }
}
