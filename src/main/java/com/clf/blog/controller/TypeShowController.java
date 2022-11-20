package com.clf.blog.controller;

import com.clf.blog.entity.Type;
import com.clf.blog.entity2.BlogQuery;
import com.clf.blog.entity2.IndexBlog;
import com.clf.blog.service.BlogService;
import com.clf.blog.service.TypeService;
import com.clf.blog.service.UserService;
import com.clf.blog.util.GetOtherData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, Model model, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        List<Type> types = typeService.selectIndexTypes2();

        //-1表示从首页导航点进来的
        if (id == -1) {
            if (types.size() > 0) {
                id = types.get(0).getId();
            }

        }
        model.addAttribute("types", types);

        PageHelper.startPage(pageNum, 5);
        List<IndexBlog> blogs = blogService.selectBlogsByTypeId(id);
        PageInfo<IndexBlog> page = new PageInfo<>(blogs);
        model.addAttribute("page", page);
        model.addAttribute("activeTypeId", id);

        GetOtherData.getNewBlogEstAndAvatar(blogService, userService, model);

        return "types";
    }
}
