package com.yunqi.common.viewService;

import com.yunqi.common.view.RollerView;
import com.yunqi.common.viewMapper.RollerViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RollerViewService {
    private RollerViewMapper RollerViewMapper;

    @Autowired
    public void setMapper(RollerViewMapper RollerViewMapper) {
        this.RollerViewMapper = RollerViewMapper;
    }

    public List<RollerView> select(String projectId) {
        return RollerViewMapper.select(projectId);
    }
}
