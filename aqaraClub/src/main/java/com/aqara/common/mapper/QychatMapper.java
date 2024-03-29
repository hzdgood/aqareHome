package com.aqara.common.mapper;

import com.aqara.common.entity.Qychat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QychatMapper {
    List<Qychat> select(Qychat Qychat);

    void insert(Qychat Qychat);

    void update(Qychat qychat);

    List<Qychat> selectByAgentId(Qychat qychat);

    void updateByAgentId(Qychat qychat);
}
