package com.yunqi.common.mapper;

import com.yunqi.common.entity.WorkTime;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkTimeMapper {
    List<WorkTime> select(WorkTime WorkTime);

    void insert(WorkTime WorkTime);

    void update(WorkTime WorkTime);

    void sign(WorkTime WorkTime);

    void depart(WorkTime WorkTime);

    void delete(Integer id, String updateName);

    void complete(Integer id, String updateName);
}
