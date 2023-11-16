package com.yunqi.common.controller;

import com.yunqi.common.entity.Tech;
import com.yunqi.common.service.TechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tech")
public class TechController {
    private TechService TechService;

    @Autowired
    public void setMapper(TechService TechService) {
        this.TechService = TechService;
    }

    @CrossOrigin
    @RequestMapping("/select")
    public List<Tech> select() {
        return TechService.select();
    }

    @CrossOrigin
    @RequestMapping("/selectId")
    public List<Tech> selectId(Integer id) {
        return TechService.selectId(id);
    }
}
