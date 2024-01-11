package com.aqara.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
@RequestMapping("/AgentAgent")
public class QyAgentController {
    private AgentProperties AgentProperties;

    private AgentService AgentService;

    @Autowired
    public void setMapper(AgentProperties AgentProperties, AgentService AgentService) {
        this.AgentProperties = AgentProperties;
        this.AgentService = AgentService;
    }

    @CrossOrigin
    @RequestMapping("/corpToken") //获取企业凭证
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
    @RequestMapping("/jsapiTicket") // JSAPI 获取应用 jsapi_ticket
    private String getJsapiTicket(String agentId) {
        Agent Agent = new Agent();
        Agent.setType("access_token");
        Agent.setAgentId(agentId);
        List<Agent> list = AgentService.selectByAgentId(Agent);
        String access_token = list.get(0).getTicket();
        JSONObject JSONObject = new JSONObject();
        String url = AgentProperties.getJsapiTicket() + "?access_token=" + access_token;
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
        Agent Agent = new Agent();
        Agent.setType("access_token");
        Agent.setAgentId(agentId);
        List<Agent> list = AgentService.selectByAgentId(Agent);
        String access_token = list.get(0).getTicket();
        JSONObject JSONObject = new JSONObject();
        String url = AgentProperties.getAppTicket() + "?access_token=" + access_token + "&type=agent_config";
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
    @RequestMapping("/followUserList") // 获取配置了客户联系功能的成员列表
    private String getFollowUserList(String agentId) {
        Agent Agent = new Agent();
        Agent.setType("access_token");
        Agent.setAgentId(agentId);
        List<Agent> list = AgentService.selectByAgentId(Agent);
        String access_token = list.get(0).getTicket();
        String str = AgentProperties.getFollowUserList() + "?access_token=" + access_token;
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
        String url = AgentProperties.getExternalContactList();
        Agent Agent = new Agent();
        Agent.setType("access_token");
        Agent.setAgentId(agentId);
        List<Agent> list = AgentService.selectByAgentId(Agent);
        String access_token = list.get(0).getTicket();
        String lastUrl = url + "?access_token=" + access_token + "&userid=" + userId;
        return HttpUtil.get(lastUrl);
    }

    @CrossOrigin
    @RequestMapping("/accessToken") // 内部人员 获取客户列表
    private String getCorpToken(String corpId, String corpSecret, String agentId) {
        JSONObject JSONObject = new JSONObject();
        String url = AgentProperties.getToken() + "?corpid=" + corpId + "&corpsecret=" + corpSecret;
        String str = HttpUtil.dataPost(url, JSONObject);
        JSONObject json = JSON.parseObject(str);
        String access_token = "";
        if (json != null) {
            access_token = json.getString("access_token");
            updateTable("access_token", access_token, "7200", agentId);
        }
        return access_token;
    }

    private String checkToken(Agent Agent) { // 检查token
        long expired = Long.parseLong(Agent.getExpires_in()) * 800;
        long date = Agent.getDate().getTime();
        long sys = System.currentTimeMillis();
        if (expired + date <= sys) { //是否过期
            return getAccessToken(Agent.getAgentId());
        } else {
            return Agent.getTicket();
        }
    }

    private String getAccessToken(String agentId) {
        Agent Agent = new Agent();
        Agent.setType("permanent_code");
        Agent.setAgentId(agentId);
        List<Agent> list = AgentService.selectByAgentId(Agent);
        Agent Agent1 = new Agent();
        Agent1.setType("corpId");
        List<Agent> list1 = AgentService.select(Agent1);
        String corpSecret = list.get(0).getTicket();
        String corpId = list1.get(0).getTicket();
        return getCorpToken(corpId, corpSecret, agentId);
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
