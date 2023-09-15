package com.yunqi.common.controller;

import com.yunqi.common.entity.Writer;
import com.yunqi.common.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/writer")
public class WriterController {
    private WriterService WriterService;
    @Autowired
    public void setMapper(WriterService WriterService) {
        this.WriterService = WriterService;
    }
    @CrossOrigin
    @RequestMapping("/insert")
    private void insert(Writer Writer) {
        WriterService.insert(Writer);
    }
    @CrossOrigin
    @RequestMapping("/update")
    private void update(Writer Writer) {
        WriterService.update(Writer);
    }
}
