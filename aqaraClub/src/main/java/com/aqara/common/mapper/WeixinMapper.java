package com.aqara.common.mapper;

import com.aqara.common.entity.Weixin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinMapper {
    List<Weixin> select();

    List<Weixin> selectAll();

    void insert(Weixin Weixin);

    void update(Weixin weixin);

    void delete(Integer id);
}
