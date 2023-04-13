package com.aqara.common.mapper;

import com.aqara.common.entity.Wechat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WechatMapper {

    List<Wechat> select(String type);

    void insert(Wechat wechat);

    void update(Wechat wechat);

    void delete(Integer id);
}
