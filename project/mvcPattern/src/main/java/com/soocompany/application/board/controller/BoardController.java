package com.soocompany.application.board.controller;

import com.soocompany.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {

    @Autowired
    private UserInfo userSession;

    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public ModelAndView Board() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("board/board");

        String userId = userSession.getUserId();
        mv.addObject("userId", userId);

        // 게시판 글 보여주기

        return mv;
    }
}
