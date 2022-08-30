package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.Logging;

@Repository
public interface LoggingMapper {
	
	void insert(Logging log);

	List<Logging> select(String name, String custom);
}
