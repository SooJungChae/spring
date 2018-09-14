package com.soocompany.application.register.dao;

import com.soocompany.application.login.model.Users;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegisterDAO {

    @Autowired
    private SqlSession sqlSession;

    // 아이디 체크
    public boolean isExistUser(String uid) {
        boolean result = true;
        Integer list;

        list = sqlSession.selectOne("user.selectId", uid);

        if (list == 0) {
            result = false;
        }

        return result;
    }



    //비밀번호 체크
//    public int checkPassword(Users user) {
//        return sqlSession.select("sign.selectUser")
//    }
}
