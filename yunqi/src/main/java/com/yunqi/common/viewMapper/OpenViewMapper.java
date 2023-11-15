package com.yunqi.common.viewMapper;

import com.yunqi.common.view.OpenView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpenViewMapper {
    List<OpenView> select(OpenView OpenView);
}
