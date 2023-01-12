package com.aqara.common.service;

import com.aqara.common.entity.Team;
import com.aqara.common.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    TeamMapper TeamMapper;

    public List<Team> select() {
        return TeamMapper.select();
    }

    public void insert(Team Team) {
        TeamMapper.insert(Team);
    }

    public void update(Team Team) {
        TeamMapper.update(Team);
    }

    public void delete(Integer id) {
        TeamMapper.delete(id);
    }

}
