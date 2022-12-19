package com.aqara.common.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.aqara.common.entity.Logging;

@Repository
public interface LoggingMapper {

    List<Logging> select(String name, String custom);

    void insert(Logging log);

    void update(Logging log);

    void delete(Integer id);
}
