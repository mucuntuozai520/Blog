package com.clf.blog.dao;

import com.clf.blog.entity.Type;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TypeRepository {

    List<Type> selectTypes();

    int saveType(Type type);

    Type getType(Long id);

    int updateType(Type type);

    int deleteType(Long id);

    Type getTypeByName(String name);

    List<Type> selectIndexTypes();

    List<Type> selectIndexTypes2();

}
