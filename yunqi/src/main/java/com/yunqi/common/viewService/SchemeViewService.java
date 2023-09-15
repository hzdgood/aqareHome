package com.yunqi.common.viewService;

import com.yunqi.common.view.SchemeView;
import com.yunqi.common.viewMapper.SchemeViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchemeViewService {
    private SchemeViewMapper SchemeViewMapper;

    @Autowired
    public void setMapper(SchemeViewMapper SchemeViewMapper) {
        this.SchemeViewMapper = SchemeViewMapper;
    }

    public List<SchemeView> select(SchemeView SchemeView) {
        return SchemeViewMapper.select(SchemeView);
    }
}
