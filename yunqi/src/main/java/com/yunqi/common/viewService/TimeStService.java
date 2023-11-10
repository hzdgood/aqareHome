package com.yunqi.common.viewService;

import com.yunqi.common.view.TimeSt;
import com.yunqi.common.viewMapper.TimeStMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeStService {

    private TimeStMapper TimeStMapper;

    @Autowired
    public void setMapper(TimeStMapper TimeStMapper) {
        this.TimeStMapper = TimeStMapper;
    }

    public List<TimeSt> selectId(TimeSt TimeSt) {
        return TimeStMapper.selectId(TimeSt);
    }
}


