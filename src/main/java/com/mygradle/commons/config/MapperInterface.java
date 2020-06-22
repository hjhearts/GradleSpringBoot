package com.mygradle.commons.config;

import com.mygradle.commons.model.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MapperInterface {
    UserVO selectUserById(String id);
}
