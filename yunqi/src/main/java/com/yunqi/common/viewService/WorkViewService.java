package com.yunqi.common.viewService;

import com.yunqi.common.view.WorkView;
import com.yunqi.common.viewMapper.WorkViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkViewService {
    private WorkViewMapper WorkViewMapper;

    @Autowired
    public void setMapper(WorkViewMapper WorkViewMapper) {
        this.WorkViewMapper = WorkViewMapper;
    }

    public List<WorkView> select(WorkView workView) {
        return WorkViewMapper.select(workView);
    }

    public List<WorkView> selectCom(WorkView workView) {
        return WorkViewMapper.selectCom(workView);
    }

    public List<WorkView> selectAll(WorkView workView) {
        return WorkViewMapper.selectAll(workView);
    }
}
