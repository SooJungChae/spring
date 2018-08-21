package com.soocompany.application.login.controller;

import com.soocompany.application.login.model.Users;
import com.soocompany.application.login.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    private SignUpService signUpService;

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
    
//    @RequestMapping(value = "/signup", method= RequestMethod.GET)
//    public String signup() { return "register/register"; }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(Users user){
        // 아이디, 비밀번호 가져와서
        // DB 에 저장
       signUpService.insertUser(user);
        // CommonMessage.SUCCESS
        return "login/login";
    }

}
