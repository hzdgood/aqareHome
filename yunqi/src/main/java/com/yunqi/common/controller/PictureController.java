package com.yunqi.common.controller;

import com.yunqi.common.entity.Picture;
import com.yunqi.common.service.PictureService;
import com.yunqi.common.utils.MultipartFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/picture")
public class PictureController {
    private com.yunqi.common.service.PictureService PictureService;

    @Autowired
    public void setMapper(PictureService PictureService) {
        this.PictureService = PictureService;
    }

    @CrossOrigin
    @RequestMapping("/selectId")
    public List<Picture> selectId(Integer id) {
        return PictureService.select();
    }

    @CrossOrigin
    @RequestMapping("/insert")
    public void insert(Picture Picture) {
        PictureService.insert(Picture);
    }
    @CrossOrigin
    @ResponseBody
    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        Random random = new Random();
        int randomInt = random.nextInt(1000);
        return MultipartFileUtils.saveMultipartFile(file, "E:/picture/" + randomInt);
    }
}
