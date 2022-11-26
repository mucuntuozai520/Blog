package com.clf.blog.controller;

import com.clf.blog.entity.Comment;
import com.clf.blog.entity.User;
import com.clf.blog.service.BlogService;
import com.clf.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.selectCommentsByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, BindingResult bindingResult, HttpSession session, Model model) {
        System.out.println("进入comments控制器....");
        //--------------
        String content = comment.getContent();
        if(content == null || content.equals("")){
            model.addAttribute("message", "评论失败!");
            model.addAttribute("comments", commentService.selectCommentsByBlogId(comment.getBlog().getId()));
            return "blog :: commentList";
        }
        User theUser = (User) session.getAttribute("theUser");
        comment.setNickname(theUser.getNickname());
        comment.setEmail(theUser.getEmail());
//------------------------
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.selectBlogById(blogId));

        User user = (User) session.getAttribute("user");

        if (user != null) {//管理员已登录
            if (theUser.getUsername().equals(user.getUsername())) {
                //设置管理员信息
                comment.setAvatar(user.getAvatar());
                comment.setAdminComment(true);
            } else {
                //普通用户，设置默认的头像
                comment.setAvatar(avatar);
                comment.setUserComment(theUser.getId());
            }
        } else {
            comment.setAvatar(avatar);
            comment.setUserComment(theUser.getId());
        }

        commentService.saveComment(comment);
        return "redirect:/comments/" + comment.getBlog().getId();
    }

}
