package com.yunqi.common.viewMapper;

import com.yunqi.common.view.WorkView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkViewMapper {
    List<WorkView> select(WorkView WorkView);
}
