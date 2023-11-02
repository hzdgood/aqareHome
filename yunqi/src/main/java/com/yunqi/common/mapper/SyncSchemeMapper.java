package com.yunqi.common.mapper;

import com.yunqi.common.entity.SyncScheme;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyncSchemeMapper {
    void insert(SyncScheme SyncScheme);

    void delete(Integer id);

    List<SyncScheme> select();
}
