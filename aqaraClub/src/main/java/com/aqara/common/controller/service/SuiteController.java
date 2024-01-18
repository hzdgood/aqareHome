package com.aqara.common.controller.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.aes.AesException;
import com.aqara.common.aes.WXBizMsgCrypt;
import com.aqara.common.entity.Qychat;
import com.aqara.common.properties.QyProperties;
import com.aqara.common.service.QychatService;
import com.aqara.common.utils.HttpUtil;
import com.aqara.common.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/suiteService")
public class SuiteController {
    private QyProperties QyProperties;

    private QychatService QychatService;

    @Autowired
    public void setMapper(QyProperties QyProperties, QychatService QychatService) {
        this.QychatService = QychatService;
        this.QyProperties = QyProperties;
    }

    @CrossOrigin
    @RequestMapping(value = "receive", method = RequestMethod.GET) // 测试成功
    public void doGetValid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg_signature = request.getParameter("msg_signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(
                QyProperties.getToken(),
                QyProperties.getEncodingAESKey(),
                QyProperties.getCorpID());
        String sEchoStr = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
        PrintWriter out = response.getWriter();
        out.print(sEchoStr);
    }

    @CrossOrigin
    @RequestMapping(value = "receive", method = RequestMethod.POST) // 企业微信回调 OK
    public void doPostValid(HttpServletRequest request, HttpServletResponse response) {
        String corpId = request.getParameter("CORPID");
        String tempStr = "";
        JSONObject json = null;
        StringBuilder postData = new StringBuilder();
        try {
            ServletInputStream in = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while (null != (tempStr = reader.readLine())) {
                postData.append(tempStr);
            }
            if (corpId != null && !corpId.isEmpty()) {
                if (corpId.equals("$CORPID$")) {
                    json = getCrypt(request, postData.toString());
                } else {
                    json = getCryptCorp(request, postData.toString());
                }
            } else {
                json = getCrypt(request, postData.toString());
            }
            PrintWriter out = response.getWriter();
            out.print("success");
        } catch (Exception e) {
            // System.out.println("suit 85");
        }
        String InfoType = null;
        if (json != null) {
            InfoType = (String) JsonUtil.findValueByKey(json, "InfoType");
        }
        Qychat Qychat = new Qychat();
        Qychat.setType(InfoType);
        if (InfoType != null) {
            if (InfoType.equals("suite_ticket")) {
                String SuiteTicket = (String) JsonUtil.findValueByKey(json, "SuiteTicket");
                String TimeStamp = (String) JsonUtil.findValueByKey(json, "TimeStamp");
                Qychat.setTicket(SuiteTicket);
                Qychat.setExpires_in(TimeStamp);
                getSuiteToken(SuiteTicket); // suite_access_token
                QychatService.update(Qychat);
            } else if (InfoType.equals("create_auth")) {
                String AuthCode = (String) JsonUtil.findValueByKey(json, "AuthCode");
                String TimeStamp = (String) JsonUtil.findValueByKey(json, "TimeStamp");
                Qychat.setTicket(AuthCode);
                Qychat.setExpires_in(TimeStamp);
                getCorpToken(AuthCode);
                QychatService.update(Qychat);
            }
        }
    }


    public JSONObject getCrypt(HttpServletRequest request, String postData) {
        JSONObject json = null;
        try {
            json = getJson(request, postData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    private JSONObject getCryptCorp(HttpServletRequest request, String postData) {
        JSONObject json = null;
        try {
            json = getJsonCorp(request, postData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    public JSONObject getJson(HttpServletRequest request, String postData) throws Exception { // OK
        WXBizMsgCrypt wrap = new WXBizMsgCrypt(QyProperties.getToken(), QyProperties.getEncodingAESKey(), QyProperties.getSuiteID());
        return getJsonObject(request, postData, wrap);
    }

    public JSONObject getJsonCorp(HttpServletRequest request, String postData) throws Exception { // OK
        WXBizMsgCrypt wrap = new WXBizMsgCrypt(QyProperties.getToken(), QyProperties.getEncodingAESKey(), QyProperties.getCorpID());
        return getJsonObject(request, postData, wrap);
    }

    private JSONObject getJsonObject(HttpServletRequest request, String postData, WXBizMsgCrypt wxcpt) throws AesException, JsonProcessingException {
        String msg_signature = request.getParameter("msg_signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String xml = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, postData);
        // System.out.println("xml:" + xml);
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode jsonNode = xmlMapper.readTree(xml);
        return JSONObject.parseObject(jsonNode.toString());
    }

    public void getCorpToken(String auth_code) {
        Qychat Qychat = new Qychat();
        Qychat.setType("suite_access_token");
        List<Qychat> list = QychatService.select(Qychat);
        String suite_access_token = list.get(0).getTicket();
        getPermanentCode(suite_access_token, auth_code);
    }

    private void getSuiteToken(String suite_ticket) { // 获取第三方应用凭证
        Qychat Qychat = new Qychat();
        JSONObject JSONObject = new JSONObject();
        JSONObject.put("suite_id", QyProperties.getSuiteID());
        JSONObject.put("suite_secret", QyProperties.getSecret());
        JSONObject.put("suite_ticket", suite_ticket);
        String str = HttpUtil.dataPost(QyProperties.getSuite_token(), JSONObject);
        JSONObject json = JSON.parseObject(str);
        String suite_access_token = "";
        if (json != null) {
            suite_access_token = (String) JsonUtil.findValueByKey(json, "suite_access_token");
            Integer expires_in = (Integer) JsonUtil.findValueByKey(json, "expires_in");
            Qychat.setType("suite_access_token");
            Qychat.setTicket(suite_access_token);
            if (expires_in != null) {
                Qychat.setExpires_in(expires_in.toString());
            }
            QychatService.update(Qychat);
        }
    }

    private void getPermanentCode(String suite_access_token, String auth_code) { // 获取企业永久授权
        JSONObject JSONObject = new JSONObject();
        JSONObject.put("auth_code", auth_code);
        String url = QyProperties.getPermanent_code() + "?suite_access_token=" + suite_access_token;
        String str = HttpUtil.dataPost(url, JSONObject);
        JSONObject json = JSON.parseObject(str);
        // System.out.println(json);
        if (json != null) {
            String permanent_code = json.getString("permanent_code");
            JSONObject edition_info = json.getJSONObject("edition_info");
            JSONArray agent = edition_info.getJSONArray("agent");
            JSONObject auth_corp_info = json.getJSONObject("auth_corp_info");
            String corpId = auth_corp_info.getString("corpid");
            String agentId = agent.getJSONObject(0).getString("agentid");
            insert("permanent_code", permanent_code, agentId);
            insert("corpId", corpId, agentId);
            insert("access_token", "", agentId);
            insert("appTicket", "", agentId);
            insert("jsapiTicket", "", agentId);
        }
    }

    public void insert(String type, String ticket, String agentId) {
        Qychat Qychat = new Qychat();
        Qychat.setType(type);
        Qychat.setTicket(ticket);
        Qychat.setAgentId(agentId);
        Qychat.setExpires_in("00000");
        QychatService.insert(Qychat);
    }
}
