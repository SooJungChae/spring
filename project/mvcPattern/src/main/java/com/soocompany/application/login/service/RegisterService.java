package com.soocompany.application.login.service;

import com.soocompany.application.CommonMessage;
import com.soocompany.application.login.dao.SignDAO;
import com.soocompany.application.login.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private SignDAO dao;

    // 아이디, 비번 DB에 저장
    public String insertUser(Users user) {

        // DAO 에서 함수 호출해서 실행한다.
        dao.insertUser(user);
//        if ( == -1) {
//            return CommonMessage.FAIL;
//        }

        return CommonMessage.SUCCESS;
    }
}
