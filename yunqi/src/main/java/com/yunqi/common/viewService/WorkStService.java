package com.yunqi.common.viewService;

import com.yunqi.common.view.WorkSt;
import com.yunqi.common.viewMapper.WorkStMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkStService {

    private WorkStMapper WorkStMapper;

    @Autowired
    public void setMapper(WorkStMapper WorkStMapper) {
        this.WorkStMapper = WorkStMapper;
    }

    public List<WorkSt> selectId(WorkSt WorkSt) {
        return WorkStMapper.selectId(WorkSt);
    }
}
