package com.aqara.common.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.aqara.common.entity.SpeedyTheme;

@Repository
public interface SpeedyThemeMapper {

    List<SpeedyTheme> select(Integer teamId, String theme, String type, String affiliatePerson);

    void insert(SpeedyTheme SpeedyTheme);

    void update(SpeedyTheme SpeedyTheme);

    void delete(List<Integer> ids);

}
