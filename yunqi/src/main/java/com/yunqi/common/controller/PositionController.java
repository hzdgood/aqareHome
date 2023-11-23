package com.yunqi.common.controller;

import com.yunqi.common.entity.Coordinate;
import com.yunqi.common.entity.Position;
import com.yunqi.common.service.PositionService;
import com.yunqi.common.utils.CoordinateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {

    private static final double EQUATOR_RADIUS = 6378137;

    private PositionService PositionService;

    @CrossOrigin
    @RequestMapping("/getDistance")
    public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 纬度
        double lat1 = Math.toRadians(latitude1);
        double lat2 = Math.toRadians(latitude2);
        // 经度
        double lon1 = Math.toRadians(longitude1);
        double lon2 = Math.toRadians(longitude2);
        // 纬度之差
        double a = lat1 - lat2;
        // 经度之差
        double b = lon1 - lon2;
        // 计算两点距离的公式
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        // 弧长乘赤道半径, 返回单位: 米
        s = s * EQUATOR_RADIUS;
        System.out.println(longitude1 + "--" + latitude1 + "-" + longitude2 + "--" + latitude2 + "---" + s);
        return s;
    }

    @Autowired
    public void setMapper(PositionService PositionService) {
        this.PositionService = PositionService;
    }

    @CrossOrigin
    @RequestMapping("/select")
    public List<Position> select(Position Position) {
        return PositionService.select(Position);
    }

    @CrossOrigin
    @RequestMapping("/add")
    public void insert(Position Position) {
        PositionService.insert(Position);
    }

    @CrossOrigin
    @RequestMapping("/getCoordinate")
    public Coordinate getCoordinate(String address) {
        return CoordinateUtil.Coordinate(address);
    }
}
