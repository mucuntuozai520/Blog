package com.clf.blog.controller.admin;

import com.clf.blog.entity.User;
import com.clf.blog.entity2.BlogQuery;
import com.clf.blog.service.BlogService;
import com.clf.blog.service.UserService;
import com.clf.blog.util.Base64Decode;
import com.clf.blog.util.Base64Encode;
import com.clf.blog.util.GetOtherData;
import com.clf.blog.util.Md5SaltEncode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie temp : cookies) {
                if (temp.getName().equals("rememberInfo")) {
                    String[] loginInfo = temp.getValue().split("-");
                    request.setAttribute("userName", loginInfo[0]);
                    request.setAttribute("password", new String(Base64Decode.base64Decode(loginInfo[1])));
                }

            }
        }

        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, String remember, HttpServletResponse response,
                        RedirectAttributes redirectAttributes, HttpServletRequest request,
                        Model model) {

        User isAdmin = userService.selectUser(username);
        //??????????????????????????????????????????
        if (isAdmin != null) {
            if (isAdmin.getType() == 1) {//1=???????????????

                //1.??????Subject
                Subject subject = SecurityUtils.getSubject();
                //2.?????????????????????????????????
                UsernamePasswordToken token = new UsernamePasswordToken(username, Md5SaltEncode.md5Hash(password, username, 3));
                try {

                    //3.??????
                    System.out.println("subject-----------" + subject);
                    subject.login(token);

                    if (remember != null) {//????????????
                        System.out.println(request.getContextPath());
                        //??????????????? base64???????????????cookie
                        Cookie cookie = new Cookie("rememberInfo", username + "-" + Base64Encode.base64Encode(password.getBytes()));
                        cookie.setMaxAge(60 * 60 * 24 * 31);//?????????
                        response.addCookie(cookie);

                    } else {
                        Cookie cookie = new Cookie("rememberInfo", "");
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }

                    //??????????????????
                    List<BlogQuery> newBlogs = blogService.selectNewBlogs();
                    model.addAttribute("newBlogs", newBlogs);
                    //???????????????????????????????????????session
                    User user = userService.selectUser(username);
                    user.setPassword(null);
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(-1);

                } catch (UnknownAccountException e) {
                    redirectAttributes.addFlashAttribute("message", "???????????????????????????");
                    return "redirect:/admin";
                } catch (IncorrectCredentialsException e) {
                    redirectAttributes.addFlashAttribute("message", "?????????????????????");
                    return "redirect:/admin";
                } catch (AuthenticationException e) {
                    return "redirect:/admin";
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return "admin/index";

            } else {
                //??????????????????????????????
                redirectAttributes.addFlashAttribute("message", "???????????????????????????");
                return "redirect:/admin";
            }

        } else {
            redirectAttributes.addFlashAttribute("message", "???????????????????????????");
            return "redirect:/admin";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        System.out.println("==============????????????============");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/admin";
    }
}
