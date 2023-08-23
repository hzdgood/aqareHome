package com.yunqi.common.mapper;

import com.yunqi.common.entity.Huoban;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HuobanMapper {
    List<Huoban> select();

    void insert(Huoban huoban);
}
