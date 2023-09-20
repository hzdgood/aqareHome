package com.yunqi.common.viewMapper;

import com.yunqi.common.view.WriterView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WriterViewMapper {
    List<WriterView> select(WriterView WriterView);
}
