package com.aqara.common.mapper;

import com.aqara.common.entity.Huoban;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HuobanMapper {

    List<Huoban> select();

    void insert(Huoban huoban);

    void delete(Integer id);

    void deleteAll(List<Integer> ids);
}
