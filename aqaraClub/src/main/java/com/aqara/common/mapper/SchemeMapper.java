package com.aqara.common.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.aqara.common.entity.Scheme;

@Repository
public interface SchemeMapper {

    List<Scheme> select();

    void insert(Scheme scheme);

    void delete(Integer id);
}
