package com.yunqi.common.service;

import com.yunqi.common.entity.Position;
import com.yunqi.common.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    private PositionMapper PositionMapper;

    @Autowired
    public void setMapper(PositionMapper PositionMapper) {
        this.PositionMapper = PositionMapper;
    }

    public void insert(Position position) {
        PositionMapper.insert(position);
    }

    public List<Position> select(Position position) {
        return PositionMapper.select(position);
    }

    public void update(Position position) { PositionMapper.update(position); }
}
