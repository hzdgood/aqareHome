package com.yunqi.common.viewMapper;

import com.yunqi.common.view.TimeSt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeStMapper {
    List<TimeSt> select(TimeSt TimeSt);
}
