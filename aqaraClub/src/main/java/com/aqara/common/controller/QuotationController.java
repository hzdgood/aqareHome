package com.aqara.common.controller;

import com.aqara.common.entity.Quotation;
import com.aqara.common.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quotation")
public class QuotationController {
    private QuotationService QuotationService;

    @Autowired
    public void setMapper(QuotationService QuotationService) {
        this.QuotationService = QuotationService;
    }

    @CrossOrigin
    @RequestMapping("/synchronize")
    public void synchronize(Quotation Quotation) {
        String code = Quotation.getItemId();
        List<Quotation> list = QuotationService.select(code);
        if (list.size() == 0) {
            QuotationService.insert(Quotation);
        } else {
            QuotationService.update(Quotation);
            QuotationService.insert(Quotation);
        }
    }
}
