package com.aqara.common.mapper;

import com.aqara.common.entity.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMapper {

    List<Team> select();

    void insert(Team Team);

    void update(Team Team);

    void delete(Integer id);

}
