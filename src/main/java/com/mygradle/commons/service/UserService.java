package com.mygradle.commons.service;

import com.mygradle.commons.model.UserVO;
import com.mygradle.commons.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<UserVO> findAllUserInfo(){
        return userRepository.getUserInfoAll();
    }

    public void dummyInfo(){
        ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
    }

    public void createUser(UserVO userVO){
        userRepository.addUserInfo(userVO);
    }

    public Iterable<? extends UserVO> findByLikeUserName(String username){
        return userRepository.findByUserNameLike(username);
    }

    public UserVO findByOneUserName(String username){
        return userRepository.findByUserName(username);
    }
}
