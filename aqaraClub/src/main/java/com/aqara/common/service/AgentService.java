package com.aqara.common.service;

import com.aqara.common.entity.Agent;
import com.aqara.common.mapper.AgentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {
    private AgentMapper AgentMapper;

    @Autowired
    public void setMapper(AgentMapper AgentMapper) {
        this.AgentMapper = AgentMapper;
    }

    public List<Agent> select(Agent Agent) {
        return AgentMapper.select(Agent);
    }

    public List<Agent> selectByAgentId(Agent Agent) {
        return AgentMapper.selectByAgentId(Agent);
    }

    public void insert(Agent Agent) {
        AgentMapper.insert(Agent);
    }

    public void update(Agent Agent) {
        AgentMapper.update(Agent);
    }

    public void updateByAgentId(Agent Agent) {
        AgentMapper.updateByAgentId(Agent);
    }
}
