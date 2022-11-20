package com.clf.blog.dao;

import com.clf.blog.entity.Tag;
import com.clf.blog.entity2.BlogAndTag2;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TagRepository {

    List<Tag> selectTags();

    int saveTag(Tag tag);

    Tag getTag(Long id);

    int updateTag(Tag tag);

    int deleteTag(Long id);

    Tag getTagByName(String name);

    List<Tag> selectIndexTags();

    List<Tag> selectIndexTags2();

    Long selectBlogId(Long id);

    List<Long> selectTagId(Long id);

    int updateTagIds(BlogAndTag2 blogAndTag2);
}
