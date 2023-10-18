package com.yunqi.common.mapper;

import com.yunqi.common.entity.WorkTime;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkTimeMapper {
    void insert(WorkTime WorkTime);

    void update(WorkTime WorkTime);

    void sign(WorkTime WorkTime);

    void depart(WorkTime WorkTime);

    void delete(Integer workId, String updateName);

    void complete(Integer id, String updateName);
}
