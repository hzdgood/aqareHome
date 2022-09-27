package com.aqara.common.controller;

import java.util.List;
import com.aqara.common.service.CollentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.aqara.common.entity.Collent;
import com.aqara.common.utils.ReadExcelUtil;

@RestController
@RequestMapping("/collent")
public class CollentController {
	
	@Autowired
	CollentService CollentService;
	
	@CrossOrigin
	@RequestMapping("/upload")
	public void upload(@RequestParam("file") MultipartFile file) {
		List<Collent> list = ReadExcelUtil.collentExcel(file);
		for (int i = 0; i < list.size(); i++) {
			Collent Collent = list.get(i);
			CollentService.insert(Collent);
		}
	}
}
