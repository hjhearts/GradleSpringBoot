package com.mygradle.commons.repository;

import com.mygradle.commons.model.UserVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private static final String MAPPER_NAME_SPACE = "sample.mapper.userMapper.";

    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<String> getUserInfoAll(){
        return sqlSessionTemplate.selectList(MAPPER_NAME_SPACE + "selectUserInfoAll");
    }

    public void addUserInfo(UserVO userVO){
        sqlSessionTemplate.insert(MAPPER_NAME_SPACE + "addUserInfo", userVO);
    }
}
