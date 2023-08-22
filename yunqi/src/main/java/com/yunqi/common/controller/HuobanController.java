package com.yunqi.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.yunqi.common.entity.Huoban;
import com.yunqi.common.properties.HuobanProperties;
import com.yunqi.common.service.HuobanService;
import com.yunqi.common.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/huoban")
public class HuobanController {
    private HuobanProperties HuobanProperties;
    private HuobanService HuobanService;

    @Autowired
    public void setMapper(HuobanService HuobanService, HuobanProperties HuobanProperties) {
        this.HuobanService = HuobanService;
        this.HuobanProperties = HuobanProperties;
    }

    @CrossOrigin
    @RequestMapping("/getTicket")
    public String getTicket() {
        String result = "";
        List<Huoban> list = HuobanService.select();
        if (list.size() == 0) {
            return insert();
        }
        Huoban Huoban = list.get(list.size() - 1); //获取最后一条数据
        long expired = Long.parseLong(Huoban.getExpired()) * 800; // 有效时间
        long date = Huoban.getDate().getTime(); // 凭证开始时间
        long sys = System.currentTimeMillis(); // 当前
        if (expired + date <= sys) { //是否过期
            return insert();
        } else {
            JSONObject json = new JSONObject();
            json.put("ticket", Huoban.getTicket());
            result = json.toString();
        }
        return result; // 后期处理到数据库
    }

    synchronized private String insert() {
        String application_id = HuobanProperties.getApplication_id();
        String application_secret = HuobanProperties.getApplication_secret();
        String userInfo = HuobanProperties.getUserInfo();
        JSONObject obj = new JSONObject();
        obj.put("application_id", application_id);
        obj.put("application_secret", application_secret);
        String result = HttpUtil.dataPost(userInfo, obj);
        JSONObject jsonObject = JSONObject.parseObject(result);
        Huoban huoban = new Huoban();
        huoban.setTicket(jsonObject.getString("ticket"));
        huoban.setExpired(jsonObject.getString("expired"));
        HuobanService.insert(huoban);
        return result;
    }
}

