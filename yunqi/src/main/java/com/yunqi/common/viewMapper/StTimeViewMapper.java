package com.yunqi.common.viewMapper;

import com.yunqi.common.view.StTimeView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StTimeViewMapper {
    List<StTimeView> selectId(Integer projectId);
}
