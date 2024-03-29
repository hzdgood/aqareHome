package com.yunqi.common.mapper;

import com.yunqi.common.entity.Writer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WriterMapper {

    List<Writer> selectNumber(Writer Writer);

    void insert(Writer Writer);

    List<Writer> select();

    void simpleWriter(Writer writer);

    void writerCom(Integer id);

    void insertHead(Writer writer);
}
