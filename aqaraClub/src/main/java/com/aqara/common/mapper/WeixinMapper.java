package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.Weixin;

@Repository
public interface WeixinMapper {
	
	List<Weixin> select();
	
	void insert(Weixin Weixin);
	
	void delete(Integer id);
}