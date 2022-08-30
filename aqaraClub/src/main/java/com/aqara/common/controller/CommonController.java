package com.aqara.common.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aqara.common.entity.Coordinate;
import com.aqara.common.utils.CoordinateUtil;

@RestController
@RequestMapping("/common")
public class CommonController {
	@CrossOrigin
	@RequestMapping("/getCoordinate")
	public Coordinate getCoordinate(@RequestBody String address) {
		return CoordinateUtil.Coordinate(address);
	}
}