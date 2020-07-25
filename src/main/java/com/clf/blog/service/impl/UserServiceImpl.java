package com.clf.blog.service.impl;

import com.clf.blog.dao.CommentRepository;
import com.clf.blog.dao.UserRepository;
import com.clf.blog.entity.User;
import com.clf.blog.service.UserService;
import com.clf.blog.util.Md5Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private HttpSession session;
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.checkUser(username, password);
        return user;
    }

    @Override
    public User selectUser(String username) {
        return userRepository.selectUser(username);
    }

    @Transactional
    @Override
    public int updateUserInfo(User user) {
        return userRepository.updateUserInfo(user);
    }

    @Override
    public String getAvatar() {
        return userRepository.getAvatar();
    }

    @Transactional
    @Override
    public int addUser(User user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return userRepository.addUser(user);
    }

    @Override
    public List<User> selectUsers(){
        return userRepository.selectUsers();
    }

    @Override
    public int deleteUser(Long id) {
        String nickname = userRepository.selectNickNameById(id);
        if(commentRepository.selectNickName(nickname)>0){
            commentRepository.deleteCommentByNickName(nickname);
        }else{
            System.out.println("该用户没有评论!");
        }

        session.removeAttribute("theUser");
        return userRepository.deleteUser(id);
    }

}
