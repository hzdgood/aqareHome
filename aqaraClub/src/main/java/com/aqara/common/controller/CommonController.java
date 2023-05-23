package com.aqara.common.controller;

import com.aqara.common.entity.Common;
import com.aqara.common.entity.Coordinate;
import com.aqara.common.service.CommonService;
import com.aqara.common.utils.CoordinateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/common")
public class CommonController {
    private CommonService CommonService;

    @Autowired
    public void setMapper(CommonService CommonService) {
        this.CommonService = CommonService;
    }

    /**
     * 日程系统获取地址
     * */
    @CrossOrigin
    @RequestMapping("/getCoordinate")
    public Coordinate getCoordinate(@RequestBody String address) {
        return CoordinateUtil.Coordinate(address);
    }

    @CrossOrigin
    @RequestMapping("/select")
    public List<Common> select(String name) {
        return CommonService.select(name);
    }

    @CrossOrigin
    @RequestMapping("/insert")
    public void insert(Common Common) {
        CommonService.insert(Common);
    }
}