package com.aqara.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.Qychat;
import com.aqara.common.properties.QyProperties;
import com.aqara.common.service.QychatService;
import com.aqara.common.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @CrossOrigin
    @RequestMapping("/corpToken") //获取企业凭证
    private String getAccessToken() {
        Qychat Qychat = new Qychat();
        Qychat.setType("permanent_code");
        List<Qychat> list = QychatService.select(Qychat);
        Qychat qychat = new Qychat();
        qychat.setType("suite_access_token");
        List<Qychat> list1 = QychatService.select(qychat);
        String permanent_code = list.get(0).getTicket();
        String suite_access_token = list1.get(0).getTicket();
        return getCorpToken(suite_access_token, permanent_code);
    }

    @CrossOrigin
    @RequestMapping("/jsapiTicket") // JSAPI 获取应用 jsapi_ticket
    private String getJsapiTicket(String type) {
        Qychat Qychat = new Qychat();
        Qychat.setType("access_token");
        List<Qychat> list = QychatService.select(Qychat);
        String access_token = checkToken(list.get(0));
        JSONObject JSONObject = new JSONObject();
        String url = QyProperties.getJsapiTicket() + "?access_token=" + access_token + "&type=agent_config";
        String str = HttpUtil.dataPost(url, JSONObject);
        JSONObject json = JSON.parseObject(str);
        String ticket = "";
        if (json != null) {
            ticket = json.getString("ticket");
            updateTable("jsapiTicket", ticket, "7200");
        }
        return ticket;
    }

    @CrossOrigin
    @RequestMapping("/AppTicket") // JSAPI
    private String getAppTicket(String type) {
        Qychat Qychat = new Qychat();
        Qychat.setType("access_token");
        List<Qychat> list = QychatService.select(Qychat);
        String access_token = checkToken(list.get(0));
        JSONObject JSONObject = new JSONObject();
        String url = QyProperties.getAppTicket() + "?access_token=" + access_token;
        String str = HttpUtil.dataPost(url, JSONObject);
        JSONObject json = JSON.parseObject(str);
        String ticket = "";
        if (json != null) {
            ticket = json.getString("ticket");
            updateTable("appTicket", ticket, "7200");
        }
        return ticket;
    }

    private String getCorpToken(String suite_access_token, String permanent_code) {
        JSONObject JSONObject = new JSONObject();
        JSONObject.put("auth_corpid", QyProperties.getCorpID());
        JSONObject.put("permanent_code", permanent_code);
        String url = QyProperties.getCorp_token() + "?suite_access_token=" + suite_access_token;
        String str = HttpUtil.dataPost(url, JSONObject);
        JSONObject json = JSON.parseObject(str);
        String access_token = "";
        if (json != null) {
            access_token = json.getString("access_token");
            updateTable("access_token", access_token, "7200");
        }
        return access_token;
    }

    private String checkToken(Qychat Qychat) { // 检查token
        long expired = Long.parseLong(Qychat.getExpires_in()) * 800;
        long date = Qychat.getDate().getTime();
        long sys = System.currentTimeMillis();
        if (expired + date <= sys) { //是否过期
            return getAccessToken();
        } else {
            return Qychat.getTicket();
        }
    }

    public void updateTable(String type, String ticket, String expires_in) {
        Qychat Qychat = new Qychat();
        Qychat.setType(type);
        Qychat.setTicket(ticket);
        Qychat.setExpires_in(expires_in);
        QychatService.update(Qychat);
    }

//    @CrossOrigin
//    @RequestMapping("/getJsSign")
//    public String getJsSign(String url) {
//        Qychat Qychat = new Qychat();
//        Qychat.setType("access_token");
//        List<Qychat> list = QychatService.select(Qychat);
//        String access_token = checkToken(list.get(0));
//        return QyUtil.JsSignatures(url, access_token, QyProperties);
//    }
//    @CrossOrigin
//    @RequestMapping("/getAppSign")
//    public String getAppSign(String url) {
//        Qychat Qychat = new Qychat();
//        Qychat.setType("access_token");
//        List<Qychat> list = QychatService.select(Qychat);
//        String access_token = checkToken(list.get(0));
//        return QyUtil.AppSignatures(url, access_token, QyProperties);
//    }
}
