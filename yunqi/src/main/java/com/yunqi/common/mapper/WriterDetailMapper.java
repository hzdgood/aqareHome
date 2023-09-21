package com.yunqi.common.mapper;

import com.yunqi.common.entity.WriterDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterDetailMapper {
    void insert(WriterDetail WriterDetail);
    void update(WriterDetail WriterDetail);
}
