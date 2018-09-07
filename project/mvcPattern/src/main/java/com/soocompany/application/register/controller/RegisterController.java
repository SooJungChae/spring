package com.soocompany.application.register.controller;

import com.soocompany.application.login.model.Users;
import com.soocompany.application.login.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/register", method= RequestMethod.GET)
    public String register() { return "register/register"; }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Users user){
        // 아이디, 비밀번호를 꼭 입력해야 한다. 가져와서

        // DB 에 저장
        registerService.insertUser(user);

        // 회원가입이 완료되었습니다.
        // CommonMessage.SUCCESS

        return "login/login";
    }
}
