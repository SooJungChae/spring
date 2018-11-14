package com.soocompany.application.login.controller;

import com.soocompany.UserInfo;
import com.soocompany.application.CommonMessage;
import com.soocompany.application.login.model.Users;
import com.soocompany.application.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService service;

    @Autowired
    private UserInfo userSession;

    // value = "/login" 와 params 의 차이는?
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "main/main";
    }

    @RequestMapping(params = "login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, Users user) {

        ModelAndView mv = new ModelAndView();

        // 해당 사용자의 로그인 정보를 가져온다.
        // 아이디, 패스워드 체크
        if (!service.checkValidUser(user)) {

            // 사용자 없으면 실패 메세지 보여주기
            mv.setViewName("main/main");
            mv.addObject("msg", CommonMessage.FAIL);
            return mv;
        }

        // 사용자 조회 성공.
        // 조회된 정보를 session 에 등록한다.
        // session.setAttribute("userId", user.getUid());
        mv.setViewName("redirect:/board");
        return mv;
    }

    @RequestMapping(params = "register", method = RequestMethod.POST)
    public String register(HttpServletRequest request) {
        return "redirect:/register";
    }
}
