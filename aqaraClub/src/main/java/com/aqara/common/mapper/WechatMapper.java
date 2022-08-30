package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.Wechat;

@Repository
public interface WechatMapper {

	public List<Wechat> select(String type);

	public void insert(Wechat wechat);
}
