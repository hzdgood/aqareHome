package com.aqara.common.service;

import com.aqara.common.entity.Qychat;
import com.aqara.common.mapper.QychatMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QychatService {
    private QychatMapper QychatMapper;

    public void setMapper(QychatMapper QychatMapper) {
        this.QychatMapper = QychatMapper;
    }


    public List<Qychat> select(Qychat Qychat) {
        return QychatMapper.select(Qychat);
    }

    public void insert(Qychat qychat) {
        QychatMapper.insert(qychat);
    }
}
