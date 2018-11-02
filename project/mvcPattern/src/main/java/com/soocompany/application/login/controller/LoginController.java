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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login/login";
    }

    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, Users user) {

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession(true);

        // 해당 사용자의 로그인 정보를 가져온다.
        if (service.loginUser(user) == 1) {
            // 조회된 정보를 session 에 등록한다.
            session.setAttribute("userId", user.getUid());
            mv.setViewName("redirect:/board");
            return mv;
            // return "redirect:/board";
        }

        // 사용자 없으면 실패 메세지 보여주기
        mv.setViewName("login/login");
        mv.addObject("msg", CommonMessage.FAIL);
        return mv;
    }
}
