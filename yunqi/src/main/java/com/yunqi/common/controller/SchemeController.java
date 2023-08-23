package com.yunqi.common.controller;

import com.yunqi.common.entity.Scheme;
import com.yunqi.common.excel.SchemeExcel;
import com.yunqi.common.service.SchemeService;
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

    @CrossOrigin
    @RequestMapping("/insert")
    public void insert(Scheme Scheme) {
        SchemeService.insert(Scheme);
    }

    @CrossOrigin
    @RequestMapping("/update")
    public void update(Scheme Scheme) {
        SchemeService.update(Scheme);
    }

    @CrossOrigin
    @RequestMapping("/delete")
    public void delete(Integer id) {
        SchemeService.delete(id);
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
