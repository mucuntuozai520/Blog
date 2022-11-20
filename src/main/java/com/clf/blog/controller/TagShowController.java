package com.clf.blog.controller;


import com.clf.blog.entity.Tag;
import com.clf.blog.entity.Type;
import com.clf.blog.entity2.BlogQuery;
import com.clf.blog.entity2.IndexBlog;
import com.clf.blog.service.BlogService;
import com.clf.blog.service.TagService;
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
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping("/tags/{id}")
    public String types(@PathVariable Long id, Model model, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        List<Tag> tags = tagService.selectIndexTags2();
        //-1表示从首页导航点进来的
        if (id == -1) {
            if (tags != null) {
                if (tags.size() > 0) {
                    id = tags.get(0).getId();
                }

            }

        }
        model.addAttribute("tags", tags);

        PageHelper.startPage(pageNum, 5);
        List<IndexBlog> blogs = blogService.selectBlogsByTagId(id);
        PageInfo<IndexBlog> page = new PageInfo<>(blogs);
        model.addAttribute("page", page);
        model.addAttribute("activeTagId", id);

        GetOtherData.getNewBlogEstAndAvatar(blogService, userService, model);

        return "tags";
    }
}
