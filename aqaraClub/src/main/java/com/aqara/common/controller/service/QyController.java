package com.aqara.common.controller.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
    private void getCorpToken(String agentId) {
        Qychat Qychat = new Qychat();
        Qychat.setType("permanent_code");
        Qychat.setAgentId(agentId);
        List<Qychat> list = QychatService.selectByAgentId(Qychat);
        Qychat qychat = new Qychat();
        qychat.setType("suite_access_token");
        List<Qychat> list1 = QychatService.select(qychat);
        String permanent_code = list.get(0).getTicket();
        String suite_access_token = list1.get(0).getTicket();
        getCorpToken(suite_access_token, permanent_code, agentId);
    }

    @CrossOrigin
    @RequestMapping("/userinfo3rd") //获取企业凭证
    private String userinfo3rd(String code) {
        Qychat Qychat = new Qychat();
        Qychat.setType("suite_access_token");
        List<Qychat> list = QychatService.select(Qychat);
        String suite_access_token = list.get(0).getTicket();
        String url = QyProperties.getUserinfo3rd() + "?suite_access_token=" + suite_access_token + "&code=" + code;
        JSONObject JSONObject = new JSONObject();
        return HttpUtil.dataPost(url, JSONObject);
    }

    @CrossOrigin
    @RequestMapping("/jsapiTicket") // JSAPI 获取应用 jsapi_ticket
    private String getJsapiTicket(String agentId) {
        Qychat Qychat = new Qychat();
        Qychat.setType("access_token");
        Qychat.setAgentId(agentId);
        List<Qychat> list = QychatService.selectByAgentId(Qychat);
        String access_token = list.get(0).getTicket();
        JSONObject JSONObject = new JSONObject();
        String url = QyProperties.getJsapiTicket() + "?access_token=" + access_token;
        String str = HttpUtil.dataPost(url, JSONObject);
        System.out.println(str);
        JSONObject json = JSON.parseObject(str);
        String ticket = "";
        if (json != null) {
            ticket = json.getString("ticket");
            updateTable("jsapiTicket", ticket, "7200", agentId);
        }
        return ticket;
    }

    @CrossOrigin
    @RequestMapping("/AppTicket") // JSAPI
    private String getAppTicket(String agentId) {
        Qychat Qychat = new Qychat();
        Qychat.setType("access_token");
        Qychat.setAgentId(agentId);
        List<Qychat> list = QychatService.selectByAgentId(Qychat);
        String access_token = list.get(0).getTicket();
        JSONObject JSONObject = new JSONObject();
        String url = QyProperties.getAppTicket() + "?access_token=" + access_token + "&type=agent_config";
        String str = HttpUtil.dataPost(url, JSONObject);
        System.out.println(str);
        JSONObject json = JSON.parseObject(str);
        String ticket = "";
        if (json != null) {
            ticket = json.getString("ticket");
            updateTable("appTicket", ticket, "7200", agentId);
        }
        return ticket;
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
        String res = HttpUtil.get(str);
        JSONObject json = JSON.parseObject(res);
        JSONArray array = null;
        if (json != null) {
            array = json.getJSONArray("follow_user");
            for (Object o : array) {
                String userId = o.toString();
            }
        }
        return res;
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

    private String getCorpToken(String suite_access_token, String permanent_code, String agentId) {
        JSONObject JSONObject = new JSONObject();
        JSONObject.put("auth_corpid", QyProperties.getCorpID());
        JSONObject.put("permanent_code", permanent_code);
        String url = QyProperties.getCorp_token() + "?suite_access_token=" + suite_access_token;
        String str = HttpUtil.dataPost(url, JSONObject);
        JSONObject json = JSON.parseObject(str);
        String access_token = "";
        if (json != null) {
            access_token = json.getString("access_token");
            updateTable("access_token", access_token, "7200", agentId);
        }
        return access_token;
    }

    private String checkToken(Qychat Qychat) { // 检查token
        long expired = Long.parseLong(Qychat.getExpires_in()) * 800;
        long date = Qychat.getDate().getTime();
        long sys = System.currentTimeMillis();
        if (expired + date <= sys) { //是否过期
            return getAccessToken(Qychat.getAgentId());
        } else {
            return Qychat.getTicket();
        }
    }

    public void updateTable(String type, String ticket, String expires_in, String agentId) {
        Qychat Qychat = new Qychat();
        Qychat.setType(type);
        Qychat.setTicket(ticket);
        Qychat.setExpires_in(expires_in);
        Qychat.setAgentId(agentId);
        QychatService.updateByAgentId(Qychat);
    }

    private String getAccessToken(String agentId) {
        Qychat Qychat = new Qychat();
        Qychat.setType("permanent_code");
        Qychat.setAgentId(agentId);
        List<Qychat> list = QychatService.selectByAgentId(Qychat);
        Qychat qychat = new Qychat();
        qychat.setType("suite_access_token");
        List<Qychat> list1 = QychatService.select(qychat);
        String permanent_code = list.get(0).getTicket();
        String suite_access_token = list1.get(0).getTicket();
        return getCorpToken(suite_access_token, permanent_code, agentId);
    }
}
