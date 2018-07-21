package com.soocompany.application.login.controller;

import com.soocompany.application.login.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login/login";
    }

    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public String login(Users user) {
        // 아이디, 비밀번호 가져와서

        // DB 에 저장
        return "";
    }
}
