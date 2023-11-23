package com.yunqi.common.mapper;

import com.yunqi.common.entity.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionMapper {

    List<Position> select(Position Position);

    void insert(Position Position);
}
