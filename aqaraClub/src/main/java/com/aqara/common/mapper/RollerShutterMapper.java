package com.aqara.common.mapper;

import com.aqara.common.entity.RollerShutter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RollerShutterMapper {

    List<RollerShutter> currentData();

    List<RollerShutter> currentData1();

    void insert(RollerShutter RollerShutter);

    void upload(RollerShutter rollerShutter);

    void delete(Integer id);
}
