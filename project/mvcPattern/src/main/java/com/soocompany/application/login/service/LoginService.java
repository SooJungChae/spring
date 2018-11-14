package com.soocompany.application.login.service;

import com.soocompany.application.login.dao.LoginDAO;
import com.soocompany.application.login.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginDAO dao;

    public int isLoginUser(Users user) {
        return dao.isLoginUser(user);
    }

    public boolean checkValidUser(Users user) {
        Integer result = dao.isValidUser(user);
        return result != 0 ? true : false;
    }
//    public Integer loginUser(Users user) {
//        return dao.isLoginUser(user);
//    }
}
