package com.mygradle.commons;

import com.mygradle.commons.model.FreeBoardVO;
import com.mygradle.commons.model.UserVO;
import com.mygradle.commons.repository.FreeBoardRepository;
import com.mygradle.commons.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class CommonsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        /* Bean Initialize Check Code
        Exclude 2020-06-17
        ApplicationContext ctx = SpringApplication.run(CommonsApplication.class, args);
        String[] beanNames = ctx.getBeanDefinitionNames();
        for(String beanName : beanNames){
            System.out.println(beanName);
        }
        */
        /* JpaRepo 1st
        Exclude 2020-06-17
        ConfigurableApplicationContext context = SpringApplication.run(CommonsApplication.class, args);
        UserRepository userRepository = context.getBean(UserRepository.class);
        userRepository.save(new UserEntity("윤사장", 60, UserEntity.UserRole.ADMIN));
        UserEntity user = userRepository.findByUserName("윤사장");
        System.out.println("age:" + user.getAge() + "\ncreatedAt:" + user.getCreatedAt());
         */
        /* JpaRepo 2nd
        Exclude 2020-06-18
        ConfigurableApplicationContext context = SpringApplication.run(CommonsApplication.class, args);
        SchoolService schoolService = context.getBean(SchoolService.class);
        schoolService.findStudentInfo();
         */
        SpringApplication.run(CommonsApplication.class);
    }
    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    FreeBoardRepository freeBoardRepository;

    @Autowired
    public void setFreeBoardRepository(FreeBoardRepository freeBoardRepository) {
        this.freeBoardRepository = freeBoardRepository;
    }

    public void run(String ... args){
        /*
        FreeBoardVO freeBoardVO = new FreeBoardVO();
        freeBoardVO.setUserName("한글테스트");
        freeBoardVO.setCategory("101");
        freeBoardVO.setContent("☜(ﾟヮﾟ☜) (☞ﾟヮﾟ)☞ ☜(ﾟヮﾟ☜) (☞ﾟヮﾟ)☞앗싸라비야");
        freeBoardVO.setTitle("Hello!");
        freeBoardRepository.registBoard(freeBoardVO);

         */
        List<String> userList = new ArrayList<>();
        userList.add("test1");
        userList.add("test2");
        userList.add("test3");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_list", userList);
        System.out.println(userRepository.findByForEach(paramMap));
    }
}
