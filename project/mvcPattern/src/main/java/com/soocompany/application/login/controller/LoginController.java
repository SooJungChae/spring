package com.soocompany.application.login.controller;

import com.soocompany.UserInfo;
import com.soocompany.application.CommonMessage;
import com.soocompany.application.login.model.Users;
import com.soocompany.application.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String login(Users user) {

        if (service.loginUser(user) == 1) {
            userSession.setUserId(user.getUid());
            return "redirect:/board";
        }

        return CommonMessage.FAIL;
    }
}
