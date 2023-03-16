package com.aqara.common.mapper;

import com.aqara.common.entity.Wechat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WechatMapper {

    public List<Wechat> select(String type);

    public void insert(Wechat wechat);

    public void update(Wechat wechat);

    public void delete(Integer id);
}
