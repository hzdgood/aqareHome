package com.yunqi.common.viewService;

import com.yunqi.common.view.OpenView;
import com.yunqi.common.viewMapper.OpenViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenViewService {

    private OpenViewMapper OpenViewMapper;

    @Autowired
    public void setMapper(OpenViewMapper OpenViewMapper) {
        this.OpenViewMapper = OpenViewMapper;
    }

    public List<OpenView> select(String projectId) {
        return OpenViewMapper.select(projectId);
    }
}
