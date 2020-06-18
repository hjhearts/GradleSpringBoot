package com.mygradle.commons.controller;

import com.mygradle.commons.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UserController {
    private static List<User> userList = new ArrayList<>();
    static {
        userList.add(new User(1, "jpub1", "user01@test.com", "remind", new Date()));
        userList.add(new User(2, "jpub2", "user02@test.com", "restart", new Date()));
        userList.add(new User(3, "jpub3", "user03@test.com", "nine", new Date()));
        userList.add(new User(4, "jpub4", "user04@test.com", "namu", new Date()));
    }

    @RequestMapping(value = "/user/{userNo}")
    public ResponseEntity<User> getUserInfo(@PathVariable(value = "userNo") int userNo){
        User user = userList.get(userNo);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user")
    public ResponseEntity<Map<String, Object>> getUserList(){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", userList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
