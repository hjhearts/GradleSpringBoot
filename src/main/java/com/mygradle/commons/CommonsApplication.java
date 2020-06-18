package com.mygradle.commons;

import com.mygradle.commons.model.UserVO;
import com.mygradle.commons.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

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
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String ...args){
        userRepository.addUserInfo(new UserVO("insertTest", "test", "4321"));
        System.out.println(userRepository.getUserInfoAll().toString());
    }

}
