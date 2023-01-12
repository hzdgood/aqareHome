package com.aqara.common.controller;

import com.aqara.common.entity.Quotation;
import com.aqara.common.service.QuotationService;
import com.aqara.common.utils.ReadExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/quotation")
public class QuotationController {

    @Autowired
    QuotationService QuotationService;

    @CrossOrigin
    @RequestMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file) {
        List<Quotation> list = ReadExcelUtil.quotationExcel(file);
        for (int i = 0; i < list.size(); i++) {
            Quotation Quotation = list.get(i);
            QuotationService.insert(Quotation);
        }
    }
}
