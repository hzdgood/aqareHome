package com.aqara.common.controller;

import com.aqara.common.entity.Qychat;
import com.aqara.common.entity.Wechat;
import com.aqara.common.properties.CommonProperties;
import com.aqara.common.properties.QyProperties;
import com.aqara.common.service.QychatService;
import com.aqara.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/qy")
public class QyController {
    private QyProperties QyProperties;

    private UserService UserService;

    private QychatService QychatService;

    @Autowired
    public void setMapper(QyProperties QyProperties, UserService UserService) {
        this.QyProperties = QyProperties;
        this.UserService = UserService;
    }
    @RequestMapping("/getToken")
    public String getToken(@RequestBody Qychat qychat) {
        String token = "";
        List<Qychat> list = QychatService.select(qychat);
        if (list.size() == 0) {
            // return insert(type);
        }
        Qychat Qychat = list.get(list.size() - 1);
        long expired = Long.parseLong(Qychat.getExpires_in()) * 800; // 有效时间
        long date = Qychat.getDate().getTime(); // 凭证开始时间
        long sys = System.currentTimeMillis(); // 当前
        if (expired + date <= sys) { //是否过期
            // return insert(type);
        } else {
            token = Qychat.getTicket();
        }
        return token;
    }

}
