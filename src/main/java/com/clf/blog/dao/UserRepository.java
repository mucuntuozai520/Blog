package com.clf.blog.dao;

import com.clf.blog.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository {

    User checkUser(String username, String password);

    User selectUser(String username);

    int updateUserInfo(User user);

    String getAvatar();

    int addUser(User user);

    List<User> selectUsers();

    int deleteUser(Long id);

    String selectNickNameById(Long id);
}
