package com.aqara.common.service;

import com.aqara.common.entity.Quotation;
import com.aqara.common.mapper.QuotationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationService {
    @Autowired
    QuotationMapper QuotationMapper;

    public List<Quotation> select(String code) {
        return QuotationMapper.select(code);
    }

    public List<Quotation> currentData() {
        return QuotationMapper.currentData();
    }

    public void insert(Quotation Quotation) {
        QuotationMapper.insert(Quotation);
    }

    public void delete(Integer id) {
        QuotationMapper.delete(id);
    }

    public void deleteAll(List<Integer> ids) {
        QuotationMapper.deleteAll(ids);
    }
}
