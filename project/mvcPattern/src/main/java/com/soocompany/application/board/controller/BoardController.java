package com.soocompany.application.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {

    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public ModelAndView Board() {
        // 로그인 아이디

        // 게시판 글 보여주기
        ModelAndView mv = new ModelAndView();
        mv.addObject("board/board");
        return mv;
    }
}