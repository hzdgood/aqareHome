package com.aqara.common.mapper;

import com.aqara.common.entity.SpeedyTheme;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeedyThemeMapper {

    List<SpeedyTheme> select(Integer teamId, String theme, String type, String affiliatePerson);

    void insert(SpeedyTheme SpeedyTheme);

    void update(SpeedyTheme SpeedyTheme);

    void delete(List<Integer> ids);

}
