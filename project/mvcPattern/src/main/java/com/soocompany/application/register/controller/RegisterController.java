package com.soocompany.application.register.controller;

import com.soocompany.application.login.model.Users;
import com.soocompany.application.register.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/register", method= RequestMethod.GET)
    public String register() {
        return "register/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Users user){
        // 아이디, 비밀번호를 꼭 입력해야 한다. 가져와서
        ModelAndView mv = new ModelAndView();
        mv.addObject(registerService.insertUser(user), "login/login");

        // 회원가입이 완료되었습니다.
        // CommonMessage.SUCCESS
        return "register/register";
    }
}
