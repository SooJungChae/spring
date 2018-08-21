package com.soocompany.application.register.controller;

import com.soocompany.application.login.model.Users;
import com.soocompany.application.login.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Users user){
        // 아이디, 비밀번호 가져와서
        // DB 에 저장
        registerService.insertUser(user);
        // CommonMessage.SUCCESS
        return "login/login";
    }
}
