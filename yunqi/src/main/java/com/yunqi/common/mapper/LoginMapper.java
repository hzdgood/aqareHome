package com.yunqi.common.mapper;

import com.yunqi.common.entity.Login;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoginMapper {
    List<Login> select(Login Login);

    void insert(Login Login);

    void update(Login Login);

    void delete(Integer id);
}
