package com.soocompany.application.login.dao;

import com.soocompany.application.login.model.Users;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SignDAO {

    // private static String NAMESPACE = "com.";

    @Autowired
    private SqlSession sqlSession;

    // 사용자 저장
    public int insertUser(Users user) {
        return sqlSession.insert("sign.insertUser", user);
    }


}
