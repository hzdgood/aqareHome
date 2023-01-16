package com.aqara.common.mapper;

import com.aqara.common.entity.Logging;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoggingMapper {

    List<Logging> select(String name, String custom);

    void insert(Logging log);

    void update(Logging log);

    void delete(Integer id);

    void deleteAll(List<Integer> ids);
}
