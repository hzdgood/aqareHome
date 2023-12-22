package com.aqara.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.aqara.common.aes.WXBizMsgCrypt;
import com.aqara.common.entity.Qychat;
import com.aqara.common.properties.QyProperties;
import com.aqara.common.service.QychatService;
import com.aqara.common.utils.CommonUtil;
import com.aqara.common.utils.QyUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/qy")
public class QyController {
    private QyProperties QyProperties;

    private QychatService QychatService;

    @Autowired
    public void setMapper(QyProperties QyProperties, QychatService QychatService) {
        this.QyProperties = QyProperties;
        this.QychatService = QychatService;
    }

    @RequestMapping("/getToken")
    public String getToken(Qychat qychat) {
        String token = "";
        List<Qychat> list = QychatService.select(qychat);
        if (list.isEmpty()) {
            return insert(qychat.getType());
        }
        Qychat Qychat = list.get(list.size() - 1);
        long expired = Long.parseLong(Qychat.getExpires_in()) * 800; // 有效时间
        long date = Qychat.getDate().getTime(); // 凭证开始时间
        long sys = System.currentTimeMillis(); // 当前
        if (expired + date <= sys) { //是否过期
            return insert(qychat.getType());
        } else {
            token = Qychat.getTicket();
        }
        return token;
    }

    synchronized private String insert(String type) {
        String res = QyUtil.getProviderToken(QyProperties, type);
        JSONObject jsonObject = JSONObject.parseObject(res);
        if (jsonObject == null) {
            return null;
        }
        Qychat Qychat = new Qychat();
        Qychat.setTicket(jsonObject.getString("access_token"));
        Qychat.setType(type);
        Qychat.setExpires_in(jsonObject.getString("expires_in"));
        QychatService.insert(Qychat);
        return jsonObject.getString("provider_access_token");
    }
}
