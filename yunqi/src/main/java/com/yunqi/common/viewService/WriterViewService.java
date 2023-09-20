package com.yunqi.common.viewService;

import com.yunqi.common.view.WriterView;
import com.yunqi.common.viewMapper.WriterViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterViewService {
    private WriterViewMapper WriterViewMapper;

    @Autowired
    public void setMapper(WriterViewMapper WriterViewMapper) {
        this.WriterViewMapper = WriterViewMapper;
    }

    public List<WriterView> select(WriterView WriterView) {
        return WriterViewMapper.select(WriterView);
    }
}
