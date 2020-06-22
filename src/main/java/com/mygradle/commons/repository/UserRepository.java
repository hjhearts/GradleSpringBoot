package com.mygradle.commons.repository;

import com.mygradle.commons.model.UserVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<String> findByUserNameLike(String username){
        return sqlSessionTemplate.selectList(MAPPER_NAME_SPACE + "findByUserNameLike", username);
    }

    public UserVO findByUserName(String username){
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        return sqlSessionTemplate.selectOne(MAPPER_NAME_SPACE + "findByUserName", params);
    }

    public List<String> findByForEach(Map<String, Object> paramMap){
        return sqlSessionTemplate.selectList(MAPPER_NAME_SPACE + "foreachUserList", paramMap);
    }
}
