package com.yunqi.common.controller;

import com.yunqi.common.entity.Picture;
import com.yunqi.common.service.PictureService;
import com.yunqi.common.utils.MultipartFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.yunqi.common.properties.PictureProperties;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/picture")
public class PictureController {
    private PictureService PictureService;

    private PictureProperties PictureProperties;

    @Autowired
    public void setMapper(PictureService PictureService) {
        this.PictureService = PictureService;
    }

    @Autowired
    public void setMapper(PictureProperties PictureProperties) {
        this.PictureProperties = PictureProperties;
    }

    @CrossOrigin
    @RequestMapping("/select")
    public List<Picture> select(Integer workId) {
        return PictureService.select(workId);
    }

    @CrossOrigin
    @RequestMapping("/insert")
    public void insert(Picture Picture) {
        PictureService.insert(Picture);
    }
    @CrossOrigin
    @ResponseBody
    @PostMapping("/upload")
    public String upload(MultipartFile[] file) {
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        return MultipartFileUtils.saveMultipartFile(file, PictureProperties.getWorkUrl() + s.format(date));
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/uploadImg")
    public String uploadImg(MultipartFile[] file) {
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        return MultipartFileUtils.saveMultipartFile(file, PictureProperties.getMeasureUrl() + s.format(date));
    }
}
