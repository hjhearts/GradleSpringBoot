package com.mygradle.commons.controller;

import com.mygradle.commons.exception.UserNotFoundException;
import com.mygradle.commons.model.User;
import com.mygradle.commons.model.UserVO;
import com.mygradle.commons.service.UserService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/userno/{userNo}")
    public ResponseEntity<User> getUserInfo(@PathVariable(value = "userNo") int userNo){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<Iterable<UserVO>> getUserList(){
        Iterable<UserVO> resultMap = userService.findAllUserInfo();
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @RequestMapping("/regist")
    public ResponseEntity<?> regist(@Validated @RequestBody UserVO userVO){
        userService.createUser(userVO);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> findByUserOne(@PathVariable("username") String username){
        UserVO user = userService.findByOneUserName(username);
        if(user == null){
            throw new UserNotFoundException("User Not Found");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
