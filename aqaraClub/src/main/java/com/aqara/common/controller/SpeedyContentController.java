package com.aqara.common.controller;

import com.aqara.common.entity.*;
import com.aqara.common.properties.WxProperties;
import com.aqara.common.service.*;
import com.aqara.common.utils.FileUtil;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/speedy/content")
public class SpeedyContentController {

	@Autowired
	SpeedyContentService SpeedyContentService;
	
	@Autowired
	WxProperties WxProperties;

	@CrossOrigin
	@RequestMapping("/select")
	public Map<String, Object> select(@RequestBody PageReq PageReq) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SpeedyContent> list = SpeedyContentService.select(PageReq.getTheme());
		map.put("pageNo", 1);
		map.put("pageSize", 10);
		map.put("totalCount", list.size());
		map.put("totalPage", list.size() / 10);
		map.put("data", list);
		return map;
	}

	@CrossOrigin
	@RequestMapping("/insert")
	public void insert(SpeedyContent SpeedyContent) {
		SpeedyContentService.insert(SpeedyContent);
	}

	@CrossOrigin
	@RequestMapping("/update")
	public void update(SpeedyContent SpeedyContent) {
		SpeedyContentService.update(SpeedyContent);
	}

	@PostMapping("/upload")
	@CrossOrigin
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file) {
		String path = WxProperties.getLocalFiles();
		return FileUtil.fileSave(file, path);
	}

	@CrossOrigin
	@RequestMapping("/delete")
	public void delete(String ids) {
		List<Integer> list = new ArrayList<Integer>();
		if (ids.contains(",")) {
			String[] a = ids.split(",");
			for (int i = 0; i < a.length; i++) {
				list.add(Integer.parseInt(a[i]));
			}
		} else {
			list.add(Integer.parseInt(ids));
		}
		SpeedyContentService.delete(list);
	}
}
