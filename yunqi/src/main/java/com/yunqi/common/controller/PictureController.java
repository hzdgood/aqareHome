package com.yunqi.common.controller;

import com.yunqi.common.entity.Picture;
import com.yunqi.common.service.PictureService;
import com.yunqi.common.utils.MultipartFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

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
    public String upload(@RequestParam("file") MultipartFile[] file) {
        String str = "";
        if(file.length > 0) {
            for( MultipartFile f: file){
                str = str + MultipartFileUtils.saveMultipartFile(f, "D:/") + "、";
            }
            str = str.substring(0,str.length() -1);
        }
        return str;
    }
}
