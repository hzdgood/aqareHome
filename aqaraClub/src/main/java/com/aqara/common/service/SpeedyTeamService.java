package com.aqara.common.service;

import com.aqara.common.entity.PageReq;
import com.aqara.common.entity.SpeedyTeam;
import com.aqara.common.mapper.SpeedyTeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeedyTeamService {
    private SpeedyTeamMapper SpeedyTeamMapper;

    @Autowired
    public void setMapper(SpeedyTeamMapper SpeedyTeamMapper) {
        this.SpeedyTeamMapper = SpeedyTeamMapper;
    }

    public List<SpeedyTeam> select(PageReq PageReq) {
        return SpeedyTeamMapper.select(PageReq.getType(), PageReq.getName(), PageReq.getPerson());
    }

    public void insert(SpeedyTeam SpeedyTeam) {
        SpeedyTeamMapper.insert(SpeedyTeam);
    }

    public void update(SpeedyTeam SpeedyTeam) {
        SpeedyTeamMapper.update(SpeedyTeam);
    }

    public void delete(List<Integer> ids) {
        SpeedyTeamMapper.delete(ids);
    }
}
