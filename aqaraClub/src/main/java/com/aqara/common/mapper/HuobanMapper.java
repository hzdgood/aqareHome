package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.Huoban;

@Repository
public interface HuobanMapper {

	void insert(Huoban huoban);
	
	List<Huoban> select();
}
