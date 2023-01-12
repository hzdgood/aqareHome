package com.aqara.common.mapper;

import com.aqara.common.entity.Quotation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotationMapper {

    List<Quotation> select(String code, String startTime, String endTime);

    List<Quotation> currentData();

    void insert(Quotation quotation);

    void delete(Integer id);
}
