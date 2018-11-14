package com.soocompany.application.login.dao;

import com.soocompany.application.login.model.Users;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO {

    @Autowired
    private SqlSession sqlSession;

    // 사용자 체크
    public int isLoginUser(Users user) {
        return sqlSession.selectOne("user.login", user);
    }

    //
    public int isValidUser(Users user) {
        return sqlSession.selectOne("user.login", user);
//        return sqlSession.
    }
}
