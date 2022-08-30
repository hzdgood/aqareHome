package com.aqara.common.mapper;

import org.springframework.stereotype.Repository;
import com.aqara.common.entity.Scheme;

@Repository
public interface SchemeMapper {
	void insert(Scheme scheme);
}
