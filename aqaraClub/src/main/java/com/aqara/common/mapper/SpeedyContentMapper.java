package com.aqara.common.mapper;

import com.aqara.common.entity.SpeedyContent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeedyContentMapper {

    List<SpeedyContent> select(Integer themeId, String team, String theme, String type, String affiliatePerson);

    void insert(SpeedyContent SpeedyContent);

    void update(SpeedyContent SpeedyContent);

    void delete(List<Integer> ids);
}
