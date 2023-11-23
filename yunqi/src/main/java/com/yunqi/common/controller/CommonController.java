package com.yunqi.common.controller;

import com.yunqi.common.entity.Coordinate;
import com.yunqi.common.utils.CoordinateUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonController {
    /**
     * 日程系统获取地址
     */
    @CrossOrigin
    @RequestMapping("/getCoordinate")
    public Coordinate getCoordinate(@RequestBody String address) {
        return CoordinateUtil.Coordinate(address);
    }
}
