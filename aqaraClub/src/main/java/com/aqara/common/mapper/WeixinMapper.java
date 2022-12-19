package com.aqara.common.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.aqara.common.entity.Weixin;

@Repository
public interface WeixinMapper {
    List<Weixin> select();

    List<Weixin> selectAll();

    List<Weixin> weekData();

    void insert(Weixin Weixin);

    void update(Weixin weixin);

    void delete(Integer id);
}
