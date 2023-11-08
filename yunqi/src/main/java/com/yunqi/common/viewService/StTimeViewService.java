package com.yunqi.common.viewService;

import com.yunqi.common.view.StTimeView;
import com.yunqi.common.viewMapper.StTimeViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StTimeViewService {
    private StTimeViewMapper StTimeViewMapper;

    @Autowired
    public void setMapper(StTimeViewMapper StTimeViewMapper) {
        this.StTimeViewMapper = StTimeViewMapper;
    }

    public List<StTimeView> selectId(Integer projectId) {
        return StTimeViewMapper.selectId(projectId);
    }
}
