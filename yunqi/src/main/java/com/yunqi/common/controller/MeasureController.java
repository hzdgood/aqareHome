package com.yunqi.common.controller;

import com.yunqi.common.entity.OpenMeasure;
import com.yunqi.common.entity.RollerMeasure;
import com.yunqi.common.service.OpenMeasureService;
import com.yunqi.common.service.RollerMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/measure")
public class MeasureController {
    private OpenMeasureService OpenMeasureService;

    private RollerMeasureService RollerMeasureService;

    @Autowired
    public void setMapper(OpenMeasureService OpenMeasureService) {
        this.OpenMeasureService = OpenMeasureService;
    }

    @Autowired
    public void setMapper(RollerMeasureService RollerMeasureService) {
        this.RollerMeasureService = RollerMeasureService;
    }

    @CrossOrigin
    @RequestMapping("/selectOpen")
    public List<OpenMeasure> selectOpen(OpenMeasure OpenMeasure) {
        return OpenMeasureService.select(OpenMeasure);
    }

    @CrossOrigin
    @RequestMapping("/selectRoller")
    public List<RollerMeasure> selectRoller(RollerMeasure RollerMeasure) {
        return RollerMeasureService.select(RollerMeasure);
    }

    @CrossOrigin
    @RequestMapping("/addOpen")
    public void addOpen(OpenMeasure OpenMeasure) {
        OpenMeasureService.insert(OpenMeasure);
    }

    @CrossOrigin
    @RequestMapping("/addRoller")
    public void addRoller(RollerMeasure RollerMeasure) {
        RollerMeasureService.insert(RollerMeasure);
    }

    @CrossOrigin
    @RequestMapping("/disabledOpen")
    public void disabledOpen(Integer id) {
        OpenMeasureService.delete(id);
    }

    @CrossOrigin
    @RequestMapping("/disabledRoller")
    public void disabledRoller(Integer id) {
        RollerMeasureService.delete(id);
    }
}
