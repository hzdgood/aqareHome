package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aqara.common.entity.Quotation;
import com.aqara.common.mapper.QuotationMapper;

@Service
public class QuotationService {
	
	@Autowired
	QuotationMapper quotationMapper;
	
	public List<Quotation> select(String code, String startTime, String endTime){
		return quotationMapper.select(code, startTime, endTime);
	}
	
	public List<Quotation> currentData (){
		return quotationMapper.currentData();
	}
	
	public void insert(Quotation quotation) {
		quotationMapper.insert(quotation);
	}
	
	public void delete(Integer id) {
		quotationMapper.delete(id);
	}
}
