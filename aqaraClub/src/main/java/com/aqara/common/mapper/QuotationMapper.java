package com.aqara.common.mapper;

import com.aqara.common.entity.Quotation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotationMapper {

    List<Quotation> select(String code);

    List<Quotation> currentData();

    void insert(Quotation Quotation);

    void deleteAll(List<Integer> ids);

    void delete(Integer id);
}
