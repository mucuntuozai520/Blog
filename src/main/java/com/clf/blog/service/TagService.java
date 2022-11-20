package com.clf.blog.service;

import com.clf.blog.entity.Tag;
import com.clf.blog.entity.Type;

import java.util.List;

public interface TagService {

    List<Tag> selectTags();

    int saveTag(Tag tag);

    Tag getTag(Long id);

    int updateTag(Tag tag);

    int deleteTag(Long id);

    List<Tag> selectTagsByIds(String ids);

    Tag getTagByName(String name);

    List<Tag> selectIndexTags();

    List<Tag> selectIndexTags2();
}
