package com.clf.blog.controller.admin;

import com.clf.blog.entity.User;
import com.clf.blog.service.BlogService;
import com.clf.blog.service.UserService;
import com.clf.blog.util.GetOtherData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UsersController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String types(Model model, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        //引入PageHelper分页插件
        PageHelper.startPage(pageNum, 5);
        List<User> users = userService.selectUsers();

        PageInfo<User> page = new PageInfo<>(users);
        model.addAttribute("page", page);

        GetOtherData.getNewBlogEstAndAvatar(blogService, userService, model);
        return "admin/users";
    }

    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {

            //            throw new RuntimeException("博客中不存在该分类，可以删除分类了！！");
            int count = userService.deleteUser(id);
            if (count > 0) {
                redirectAttributes.addFlashAttribute("message", "删除成功！");
            } else {
                redirectAttributes.addFlashAttribute("message", "删除失败！");
            }


        return "redirect:/admin/users";
    }
}
