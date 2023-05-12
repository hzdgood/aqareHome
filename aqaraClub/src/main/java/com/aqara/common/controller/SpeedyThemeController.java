package com.aqara.common.controller;

import com.aqara.common.entity.PageReq;
import com.aqara.common.entity.SpeedyTheme;
import com.aqara.common.service.SpeedyThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/speedy/theme")
public class SpeedyThemeController {
    private SpeedyThemeService SpeedyThemeService;

    @Autowired
    public void setMapper(SpeedyThemeService SpeedyThemeService) {
        this.SpeedyThemeService = SpeedyThemeService;
    }

    @CrossOrigin
    @RequestMapping("/select")
    public Map<String, Object> select(@RequestBody PageReq PageReq) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<SpeedyTheme> list = SpeedyThemeService.select(PageReq);
        if (PageReq.getPageNo() != null || PageReq.getPageSize() != null) {
            Integer pageSize = Integer.parseInt(PageReq.getPageSize());
            Integer pageNo = Integer.parseInt(PageReq.getPageNo());
            map.put("pageNo", pageNo);
            map.put("pageSize", pageSize);
            map.put("totalCount", list.size());
            map.put("totalPage", list.size() / pageSize);
        }
        map.put("data", list);
        return map;
    }

    @CrossOrigin
    @RequestMapping("/insert")
    public void insert(SpeedyTheme SpeedyTheme) {
        SpeedyThemeService.insert(SpeedyTheme);
    }

    @CrossOrigin
    @RequestMapping("/update")
    public void update(SpeedyTheme SpeedyTheme) {
        SpeedyThemeService.update(SpeedyTheme);
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
        SpeedyThemeService.delete(list);
    }
}
