package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.Quotation;

@Repository
public interface QuotationMapper {
	
	List<Quotation> select(String code, String startTime, String endTime);
	
	List<Quotation> currentData();
	
	void insert(Quotation quotation);
	
	void upload(Quotation quotation);
	
	void delete(Integer id);
}
