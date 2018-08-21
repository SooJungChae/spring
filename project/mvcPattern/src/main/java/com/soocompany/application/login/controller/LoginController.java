package com.soocompany.application.login.controller;

import com.soocompany.application.login.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login/login";
    }

    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public String login(Users user) {
        // db 에서 userid, password 체크
        // 데이터, view
        return "redirect:/board";
    }

    // 회원가입 창으로 이동
    @RequestMapping(value = "/register", method= RequestMethod.GET)
    public String register() { return "register/register"; }
}
