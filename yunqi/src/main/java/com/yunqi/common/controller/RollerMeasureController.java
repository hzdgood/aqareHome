package com.yunqi.common.controller;

import com.yunqi.common.entity.Logging;
import com.yunqi.common.entity.RollerMeasure;
import com.yunqi.common.service.LoggingService;
import com.yunqi.common.service.RollerMeasureService;
import com.yunqi.common.view.RollerView;
import com.yunqi.common.viewService.RollerViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/measure")
public class RollerMeasureController {

    private RollerMeasureService RollerMeasureService;
    private  RollerViewService RollerViewService;
    private LoggingService LoggingService;

    @Autowired
    public void setMapper(RollerMeasureService RollerMeasureService) {
        this.RollerMeasureService = RollerMeasureService;
    }

    @Autowired
    public void setMapper(RollerViewService RollerViewService) {
        this.RollerViewService = RollerViewService;
    }

    @Autowired
    public void setMapper(LoggingService LoggingService) {
        this.LoggingService = LoggingService;
    }

    @CrossOrigin
    @RequestMapping("/selectRoller")
    public List<RollerMeasure> selectRoller(RollerMeasure RollerMeasure) {
        return RollerMeasureService.select(RollerMeasure);
    }

    @CrossOrigin
    @RequestMapping("/addRoller")
    public void addRoller(RollerMeasure RollerMeasure) {
        Logging Logging = new Logging();
        Logging.setName(RollerMeasure.getTechId() + "");
        Logging.setCustom(RollerMeasure.getProjectName());
        Logging.setInfo("上传卷帘导轨！");
        LoggingService.insert(Logging);
        RollerMeasureService.insert(RollerMeasure);
    }

    @CrossOrigin
    @RequestMapping("/rollerScheme")
    public List<RollerView> select(RollerView RollerView) {
        return RollerViewService.select(RollerView);
    }

    @CrossOrigin
    @RequestMapping("/disabledRoller")
    public void disabledRoller(Integer id) {
        RollerMeasureService.delete(id);
    }


    @CrossOrigin
    @RequestMapping("/uploadRoller")
    public List<String> uploadImg(RollerMeasure RollerMeasure) {
        List<RollerMeasure> list = RollerMeasureService.select(RollerMeasure);
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
