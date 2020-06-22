package com.mygradle.commons.repository;

import com.mygradle.commons.model.FreeBoardVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FreeBoardRepository {
    private static final String MAPPER_NAME_SPACE = "sample.mapper.freeBoard.";

    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public void registBoard(FreeBoardVO freeBoardVO){
        sqlSessionTemplate.insert(MAPPER_NAME_SPACE + "insertBoard", freeBoardVO);
    }
}
