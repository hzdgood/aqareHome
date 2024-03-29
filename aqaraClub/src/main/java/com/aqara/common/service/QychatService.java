package com.aqara.common.service;

import com.aqara.common.entity.Qychat;
import com.aqara.common.mapper.QychatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QychatService {
    private QychatMapper QychatMapper;

    @Autowired
    public void setMapper(QychatMapper QychatMapper) {
        this.QychatMapper = QychatMapper;
    }

    public List<Qychat> select(Qychat Qychat) {
        return QychatMapper.select(Qychat);
    }

    public List<Qychat> selectByAgentId(Qychat Qychat) {
        return QychatMapper.selectByAgentId(Qychat);
    }

    public void insert(Qychat qychat) {
        QychatMapper.insert(qychat);
    }

    public void update(Qychat qychat) {
        QychatMapper.update(qychat);
    }

    public void updateByAgentId(Qychat qychat) {
        QychatMapper.updateByAgentId(qychat);
    }
}
