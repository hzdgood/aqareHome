package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aqara.common.entity.Huoban;
import com.aqara.common.mapper.HuobanMapper;

@Service
public class HuobanService {
	@Autowired
	HuobanMapper HuobanMapper;

	public void insert(Huoban huoban) {
		HuobanMapper.insert(huoban);
	}

	public List<Huoban> select() {
		return HuobanMapper.select();
	}
}
