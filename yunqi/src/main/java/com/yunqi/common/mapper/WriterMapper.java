package com.yunqi.common.mapper;

import com.yunqi.common.entity.Writer;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterMapper {
    void insert(Writer Writer);

    void update(Writer Writer);
}
