package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.RollerShutter;

@Repository
public interface RollerShutterMapper {
	
	List<RollerShutter> currentData();
	
	void insert(RollerShutter RollerShutter);
	
	void upload(RollerShutter RollerShutter);

}
