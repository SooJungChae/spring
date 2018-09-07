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
        List list = null;

        try {
            list = sqlSession.selectOne("user.selectId", uid);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            sqlSession.close();
        }

        if (list == null) {
            result = false;
        }

        return result;
    }



    //비밀번호 체크
//    public int checkPassword(Users user) {
//        return sqlSession.select("sign.selectUser")
//    }
}
