package com.soocompany.application.login.service;

import com.soocompany.application.CommonMessage;
import com.soocompany.application.login.model.Users;
import com.soocompany.application.register.dao.RegisterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private RegisterDAO dao;

    // 아이디, 비번 DB에 저장
    public String insertUser(Users user) {

        // 사용자 존재유무 체크
        if (dao.isExistUser(user.getUid())) {
            return CommonMessage.FAIL;
        }

        dao.insertUser(user);

        return CommonMessage.SUCCESS;
    }
}
