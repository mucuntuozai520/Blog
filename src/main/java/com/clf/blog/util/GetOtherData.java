package com.clf.blog.util;

import com.clf.blog.entity2.BlogQuery;
import com.clf.blog.service.BlogService;
import com.clf.blog.service.UserService;
import org.springframework.ui.Model;

import java.util.List;

public class GetOtherData {

    public static void getNewBlogEstAndAvatar(BlogService blogService, UserService userService, Model model) {
        //获取最新博客信息
        List<BlogQuery> newBlogs = blogService.selectNewBlogs();

        model.addAttribute("newBlogs", newBlogs);
        //获取管理员的头像地址
        model.addAttribute("avatar", userService.getAvatar());
    }
}
