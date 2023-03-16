package com.aqara.common.mapper;

import com.aqara.common.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> select(String userName, String engName);

    List<User> selectTech();

    void update(User user);

    void insert(User user);

    void delete();

    void deleteAll(List<Integer> ids);
}
