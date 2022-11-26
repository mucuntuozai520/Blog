package com.clf.blog.service;

import com.clf.blog.entity.User;

import java.util.List;

public interface UserService {
    User checkUser(String username, String password);

    User selectUser(String username);

    int updateUserInfo(User user);

    String getAvatar();

    int addUser(User user);

    List<User> selectUsers();

    int deleteUser(Long id);

    int updatePassword(User user);

}
