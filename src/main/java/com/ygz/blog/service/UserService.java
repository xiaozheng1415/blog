package com.ygz.blog.service;

import com.ygz.blog.po.User;

public interface UserService {
    User checkUser(String username, String password);
    int updateViews();
}
