package com.ygz.blog.dao;

import com.ygz.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);
    @Transactional
    @Modifying
    @Query("update User u set u.views=u.views+1 where u.id=1")
    int updateViews();
}
