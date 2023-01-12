package com.aqara.common.mapper;

import com.aqara.common.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> select(String userName, String engName);

    List<User> selectCode(String code);

    void update(User user);

    void insert(User user);

    void delete();
}
