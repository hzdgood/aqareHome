package com.aqara.common.controller;

import com.aqara.common.entity.Weixin;
import com.aqara.common.service.WeixinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weixin")
public class WeixinController {
    private WeixinService WeixinService;

    @Autowired
    public void setMapper(WeixinService WeixinService) {
        this.WeixinService = WeixinService;
    }

    @CrossOrigin
    @RequestMapping("/selectAll")
    public List<Weixin> selectAll() {
        return WeixinService.selectAll();
    }

    @CrossOrigin
    @RequestMapping("/update")
    public void update(Weixin Weixin) {
        WeixinService.update(Weixin);
    }

    @CrossOrigin
    @RequestMapping("/insert")
    public void insert(Weixin Weixin) {
        WeixinService.insert(Weixin);
    }

    @CrossOrigin
    @RequestMapping("/delete")
    public void update(Integer id) {
        WeixinService.delete(id);
    }

    @CrossOrigin
    @RequestMapping("/synchronize")
    public void synchronize(Weixin Weixin) {
        String code = Weixin.getUuid();
        List<Weixin> list = WeixinService.selectId(code);
        if (list.size() == 0) {
            WeixinService.insert(Weixin);
        } else {
            WeixinService.update(Weixin);
            WeixinService.insert(Weixin);
        }
    }
}
