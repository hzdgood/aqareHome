package com.aqara.common.service;

import com.aqara.common.entity.Quotation;
import com.aqara.common.mapper.QuotationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationService {

    @Autowired
    QuotationMapper quotationMapper;

    public List<Quotation> select(String code, String startTime, String endTime) {
        return quotationMapper.select(code, startTime, endTime);
    }

    public List<Quotation> currentData() {
        return quotationMapper.currentData();
    }

    public void insert(Quotation quotation) {
        quotationMapper.insert(quotation);
    }

    public void delete(Integer id) {
        quotationMapper.delete(id);
    }
}
