package com.ygz.blog.service;

import com.ygz.blog.dao.UserRepository;
import com.ygz.blog.po.User;
import com.ygz.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User checkUser(String username, String password) {
        User user= userRepository.findByUsernameAndPassword(username, MD5Utils.code(password)) ;//MD5加密
        return user;
    }

    @Override
    public int updateViews() {
        return userRepository.updateViews();
    }
}
