package com.yunqi.common.viewMapper;

import com.yunqi.common.view.WorkSt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkStMapper {
    List<WorkSt> select(WorkSt WorkSt);
}
