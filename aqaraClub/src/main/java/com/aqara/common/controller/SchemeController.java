package com.aqara.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.aqara.common.entity.Scheme;
import com.aqara.common.service.SchemeService;
import com.aqara.common.utils.ReadExcelUtil;

@Controller
@RequestMapping("/file")
public class SchemeController {
    @Autowired
    SchemeService schemeService;

    @PostMapping("/upload")
    @CrossOrigin
    @ResponseBody
    public List<Scheme> upload(@RequestParam("file") MultipartFile file) {
        List<Scheme> list = ReadExcelUtil.ReadExcel(file); // 读取文件
        for (int i = 0; i < list.size(); i++) {
            Scheme scheme = list.get(i);
            schemeService.insert(scheme);
        }
        return list;
    }
}
