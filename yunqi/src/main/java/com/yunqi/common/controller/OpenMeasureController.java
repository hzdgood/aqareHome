package com.yunqi.common.controller;

import com.yunqi.common.entity.OpenMeasure;
import com.yunqi.common.service.OpenMeasureService;
import com.yunqi.common.view.OpenView;
import com.yunqi.common.viewService.OpenViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/measure")
public class OpenMeasureController {
    private OpenMeasureService OpenMeasureService;

    private OpenViewService OpenViewService;

    @Autowired
    public void setMapper(OpenMeasureService OpenMeasureService) {
        this.OpenMeasureService = OpenMeasureService;
    }

    @CrossOrigin
    @RequestMapping("/selectOpen")
    public List<OpenMeasure> selectOpen(OpenMeasure OpenMeasure) {
        return OpenMeasureService.select(OpenMeasure);
    }

    @CrossOrigin
    @RequestMapping("/addOpen")
    public void addOpen(OpenMeasure OpenMeasure) {
        OpenMeasureService.insert(OpenMeasure);
    }

    @CrossOrigin
    @RequestMapping("/openScheme")
    public List<OpenView> select(OpenView OpenView) {
        return OpenViewService.select(OpenView);
    }

    @CrossOrigin
    @RequestMapping("/disabledOpen")
    public void disabledOpen(Integer id) {
        OpenMeasureService.delete(id);
    }

    @CrossOrigin
    @RequestMapping("/uploadOpen")
    public List<String> uploadImg(OpenMeasure OpenMeasure) {
        List<OpenMeasure> list = OpenMeasureService.select(OpenMeasure);
        String url = list.get(0).getImgUrl();
        String[] str = url.split(",");
        List<String> data = new ArrayList<>();
        for (String s: str) {
            if (!Objects.equals(s, "") && s != null) {
                data.add(s);
            }
        }
        return data;
    }
}
