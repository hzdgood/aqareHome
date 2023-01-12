package com.aqara.common.mapper;

import com.aqara.common.entity.SpeedyTeam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeedyTeamMapper {

    List<SpeedyTeam> select(String type, String name, String affiliatePerson);

    void insert(SpeedyTeam SpeedyTheme);

    void update(SpeedyTeam SpeedyTheme);

    void delete(List<Integer> ids);

}
