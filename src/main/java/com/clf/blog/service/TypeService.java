package com.clf.blog.service;

import com.clf.blog.entity.Type;
import java.util.List;

public interface TypeService {
    //分页查分类
    List<Type> selectTypes();
    //增加分类
    int saveType(Type type);
    //查询分类
    Type getType(Long id);
    //更新分类
    int updateType(Type type);
    //删除分类
    int deleteType(Long id);
    //根据名字查询分类
    Type getTypeByName(String name);

    List<Type> selectIndexTypes();

    List<Type> selectIndexTypes2();
}
