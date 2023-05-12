package com.aqara.common.controller;

import com.aqara.common.entity.Scheme;
import com.aqara.common.excel.SchemeExcel;
import com.aqara.common.service.SchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/file")
public class SchemeController {
    private SchemeService SchemeService;

    @Autowired
    public void setMapper(SchemeService SchemeService) {
        this.SchemeService = SchemeService;
    }

    @PostMapping("/upload")
    @CrossOrigin
    @ResponseBody
    public List<Scheme> upload(@RequestParam("file") MultipartFile file) {
        List<Scheme> list = SchemeExcel.schemeExcel(file); // 读取文件
        for (int i = 0; i < list.size(); i++) {
            Scheme scheme = list.get(i);
            SchemeService.insert(scheme);
        }
        return list;
    }
}
