package com.yunqi.common.viewMapper;

import com.yunqi.common.view.RollerView;

import java.util.List;

public interface RollerViewMapper {
    List<RollerView> select(String projectId);
}
