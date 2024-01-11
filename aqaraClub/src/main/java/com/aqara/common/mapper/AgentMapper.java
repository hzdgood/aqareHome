package com.aqara.common.mapper;

import com.aqara.common.entity.Agent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentMapper {
    List<Agent> select(Agent Agent);

    void insert(Agent Agent);

    void update(Agent Agent);

    List<Agent> selectByAgentId(Agent Agent);

    void updateByAgentId(Agent Agent);
}
