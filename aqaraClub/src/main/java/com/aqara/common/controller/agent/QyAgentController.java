package com.aqara.common.controller.agent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.Agent;
import com.aqara.common.properties.AgentProperties;
import com.aqara.common.service.AgentService;
import com.aqara.common.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/QyAgent")
public class QyAgentController {
    private AgentProperties AgentProperties;

    private AgentService AgentService;

    @Autowired
    public void setMapper(AgentProperties AgentProperties, AgentService AgentService) {
        this.AgentProperties = AgentProperties;
        this.AgentService = AgentService;
    }

    @CrossOrigin
    @RequestMapping("/corpToken") //获取企业凭证 OK
    private void getCorpToken(String agentId) {
        Agent Agent = new Agent();
        Agent.setType("permanent_code");
        Agent.setAgentId(agentId);
        List<Agent> list = AgentService.selectByAgentId(Agent);
        Agent Agent1 = new Agent();
        Agent1.setType("corpId");
        Agent1.setAgentId(agentId);
        List<Agent> list1 = AgentService.selectByAgentId(Agent1);
        String corpSecret = list.get(0).getTicket();
        String corpId = list1.get(0).getTicket();
        getCorpToken(corpId, corpSecret, agentId);
    }

    @CrossOrigin
    @RequestMapping("/userinfo") // 企业成员信息 userId OK
    private String getUserinfo(String agentId, String code) {
        Agent Agent = new Agent();
        Agent.setType("access_token");
        Agent.setAgentId(agentId);
        List<Agent> list = AgentService.selectByAgentId(Agent);
        String access_token = list.get(0).getTicket();
        String url = AgentProperties.getUserinfo() + "?access_token=" + access_token + "&code=" + code;
        return HttpUtil.get(url);
    }

    @CrossOrigin
    @RequestMapping("/jsapiTicket") // JSAPI 获取应用 jsapi_ticket OK
    private String getJsapiTicket(String agentId) {
        Agent Agent = new Agent();
        Agent.setType("access_token");
        Agent.setAgentId(agentId);
        List<Agent> list = AgentService.selectByAgentId(Agent);
        String access_token = list.get(0).getTicket();
        JSONObject JSONObject = new JSONObject();
        String url = AgentProperties.getJsapiTicket() + "?access_token=" + access_token;
        String str = HttpUtil.dataPost(url, JSONObject);
        JSONObject json = JSON.parseObject(str);
        String ticket = "";
        if (json != null) {
            ticket = json.getString("ticket");
            updateTable("jsapiTicket", ticket, "7200", agentId);
        }
        return ticket;
    }

    @CrossOrigin
    @RequestMapping("/AppTicket") // JSAPI OK
    private String getAppTicket(String agentId) {
        Agent Agent = new Agent();
        Agent.setType("access_token");
        Agent.setAgentId(agentId);
        List<Agent> list = AgentService.selectByAgentId(Agent);
        String access_token = list.get(0).getTicket();
        JSONObject JSONObject = new JSONObject();
        String url = AgentProperties.getAppTicket() + "?access_token=" + access_token + "&type=agent_config";
        String str = HttpUtil.dataPost(url, JSONObject);
        JSONObject json = JSON.parseObject(str);
        String ticket = "";
        if (json != null) {
            ticket = json.getString("ticket");
            updateTable("appTicket", ticket, "7200", agentId);
        }
        return ticket;
    }

    @CrossOrigin
    @RequestMapping("/externalContact") // 外部人员 获取客户详情 OK
    private String getExternalContact(String userId, String agentId) {
        String url = AgentProperties.getExternalContact();
        Agent Agent = new Agent();
        Agent.setType("access_token");
        Agent.setAgentId(agentId);
        List<Agent> list = AgentService.selectByAgentId(Agent);
        String access_token = list.get(0).getTicket();
        String lastUrl = url + "?access_token=" + access_token + "&external_userid=" + userId;
        return HttpUtil.get(lastUrl);
    }

    @CrossOrigin
    @RequestMapping("/compUser") // 内部人员 获取客户详情
    private String compUser(String userId, String agentId) {
        String url = AgentProperties.getCompUser();
        Agent Agent = new Agent();
        Agent.setType("access_token");
        Agent.setAgentId(agentId);
        List<Agent> list = AgentService.selectByAgentId(Agent);
        String access_token = list.get(0).getTicket();
        String lastUrl = url + "?access_token=" + access_token + "&userid=" + userId;
        return HttpUtil.get(lastUrl);
    }

    @CrossOrigin
    @RequestMapping("/userDetail") // 内部人员 访问用户敏感信息
    private String userInfo(String user_ticket, String agentId) {
        JSONObject JSONObject = new JSONObject();
        JSONObject.put("user_ticket", user_ticket);
        String url = AgentProperties.getUserdetail();
        Agent Agent = new Agent();
        Agent.setType("access_token");
        Agent.setAgentId(agentId);
        List<Agent> list = AgentService.selectByAgentId(Agent);
        String access_token = list.get(0).getTicket();
        String lastUrl = url + "?access_token=" + access_token;
        return HttpUtil.dataPost(lastUrl, JSONObject);
    }

    private void getCorpToken(String corpId, String corpSecret, String agentId) { // OK
        JSONObject JSONObject = new JSONObject();
        String url = AgentProperties.getGetToken() + "?corpid=" + corpId + "&corpsecret=" + corpSecret;
        String str = HttpUtil.dataPost(url, JSONObject);
        JSONObject json = JSON.parseObject(str);
        String access_token = "";
        if (json != null) {
            access_token = json.getString("access_token");
            updateTable("access_token", access_token, "7200", agentId);
        }
    }

    public void updateTable(String type, String ticket, String expires_in, String agentId) {
        Agent Agent = new Agent();
        Agent.setType(type);
        Agent.setTicket(ticket);
        Agent.setExpires_in(expires_in);
        Agent.setAgentId(agentId);
        AgentService.updateByAgentId(Agent);
    }
}
