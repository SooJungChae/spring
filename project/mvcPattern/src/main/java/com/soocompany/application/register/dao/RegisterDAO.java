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

    public Integer isExistUser(String uid) {
        return sqlSession.selectOne("user.selectId", uid);
    }

    public Integer insertUser(Users user) {
        Integer result = -1;

        try {
            result = sqlSession.insert("user.insert", user);
        }
        catch (Exception ex) {
            return result;
        }

        return result;
    }



    //비밀번호 체크
//    public int checkPassword(Users user) {
//        return sqlSession.select("sign.selectUser")
//    }
}
