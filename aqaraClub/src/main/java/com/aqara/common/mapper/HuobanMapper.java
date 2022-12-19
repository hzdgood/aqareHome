package com.aqara.common.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.aqara.common.entity.Huoban;

@Repository
public interface HuobanMapper {

    List<Huoban> select();

    void insert(Huoban huoban);

    void delete(Integer id);
}
