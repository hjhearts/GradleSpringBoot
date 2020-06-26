package com.mygradle.commons.daoTest;

import com.mygradle.commons.CommonsApplication;
import com.mygradle.commons.config.MyBatisConfig;
import com.mygradle.commons.model.UserVO;
import com.mygradle.commons.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
@Transactional
public class MyBatisConfigTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testList(){
        System.out.println(userRepository.getUserInfoAll());
    }

    @Test
    public void createUser(){
        UserVO userVO = new UserVO("jPub115", "한주성", "tpsabre@test.com");
        userRepository.addUserInfo(userVO);
        System.out.println(userRepository.getUserInfoAll());
    }
}
