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
@RequestMapping("/other")
public class OtherController {
    private com.aqara.common.properties.QyProperties QyProperties;

    private com.aqara.common.service.QychatService QychatService;

    @Autowired
    public void setMapper(QyProperties QyProperties, QychatService QychatService) {
        this.QyProperties = QyProperties;
        this.QychatService = QychatService;
    }

    @CrossOrigin
    @RequestMapping("/corpToken") //获取企业凭证
    private String getCorpToken(String agentId, String permanent_code) {
        Qychat qychat = new Qychat();
        qychat.setType("suite_access_token");
        List<Qychat> list = QychatService.select(qychat);
        String suite_access_token = list.get(0).getTicket();
        Qychat qychat1 = new Qychat();
        qychat.setType("corpId");
        qychat.setAgentId(agentId);
        List<Qychat> list1 = QychatService.selectByAgentId(qychat1);
        String corpId = list1.get(0).getTicket();
        return getCorpToken(suite_access_token, permanent_code, agentId, corpId);
    }

    @CrossOrigin
    @RequestMapping("/followUserList") // 获取配置了客户联系功能的成员列表
    private String getFollowUserList(String agentId) {
        String url = QyProperties.getFollowUserList();
        Qychat Qychat = new Qychat();
        Qychat.setType("access_token");
        Qychat.setAgentId(agentId);
        List<Qychat> list = QychatService.selectByAgentId(Qychat);
        String access_token = list.get(0).getTicket();
        String str = url + "?access_token=" + access_token;
        return HttpUtil.get(str);
    }

    @CrossOrigin
    @RequestMapping("/externalContactList") // 内部人员 获取客户列表
    private String getExternalContactList(String userId, String agentId) {
        String url = QyProperties.getExternalContactList();
        Qychat Qychat = new Qychat();
        Qychat.setType("access_token");
        Qychat.setAgentId(agentId);
        List<Qychat> list = QychatService.selectByAgentId(Qychat);
        String access_token = list.get(0).getTicket();
        String lastUrl = url + "?access_token=" + access_token + "&userid=" + userId;
        return HttpUtil.get(lastUrl);
    }

    @CrossOrigin
    @RequestMapping("/externalContact") // 外部人员 获取客户详情
    private String getExternalContact(String userId, String agentId) {
        String url = QyProperties.getExternalContact();
        Qychat Qychat = new Qychat();
        Qychat.setType("access_token");
        Qychat.setAgentId(agentId);
        List<Qychat> list = QychatService.selectByAgentId(Qychat);
        String access_token = list.get(0).getTicket();
        String lastUrl = url + "?access_token=" + access_token + "&external_userid=" + userId;
        return HttpUtil.get(lastUrl);
    }

    public String getCorpToken(String suite_access_token, String permanent_code, String agentId, String corpId) {
        JSONObject JSONObject = new JSONObject();
        JSONObject.put("auth_corpid", corpId);
        JSONObject.put("permanent_code", permanent_code);
        String url = QyProperties.getCorp_token() + "?suite_access_token=" + suite_access_token;
        String str = HttpUtil.dataPost(url, JSONObject);
        JSONObject json = JSON.parseObject(str);
        String access_token = "";
        if (json != null) {
            access_token = json.getString("access_token");
            updateTable("access_token", access_token, "7200", agentId);
        }
        return str;
    }

    public void updateTable(String type, String ticket, String expires_in, String agentId) {
        Qychat Qychat = new Qychat();
        Qychat.setType(type);
        Qychat.setTicket(ticket);
        Qychat.setExpires_in(expires_in);
        Qychat.setAgentId(agentId);
        QychatService.updateByAgentId(Qychat);
    }
}
