package com.aqara.common.controller;

import com.alibaba.fastjson.JSON;
import com.aqara.common.aes.AesException;
import com.aqara.common.service.QychatService;
import com.aqara.common.entity.Qychat;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.aes.WXBizMsgCrypt;
import com.aqara.common.properties.QyProperties;
import com.aqara.common.utils.HttpUtil;
import com.aqara.common.utils.JsonUtil;
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
@RequestMapping("/suite")
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
        String id = "";
        if(corpId != null && !corpId.isEmpty()){
            id = corpId; // 测试企业CorpId
            if(corpId.equals("$CORPID$")){
                id = QyProperties.getCorpID();
            }
        } else {
            id = QyProperties.getSuiteID();
        }
        JSONObject json = null;
        try {
            json = getJson(request, id);
            PrintWriter out = response.getWriter();
            out.print("success");
        } catch (Exception e) {
            String msg_signature = request.getParameter("msg_signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            System.out.println("msg_signature:" + msg_signature);
            System.out.println("timestamp:" + timestamp);
            System.out.println("nonce:" + nonce);
            System.out.println("corpId:" + corpId);
            System.out.println("corp xml");
        }
        String InfoType = null;
        if (json != null) {
            InfoType = (String) JsonUtil.findValueByKey(json, "InfoType");
        }
        Qychat Qychat = new Qychat();
        Qychat.setType(InfoType);
        if(InfoType != null) {
            if(InfoType.equals("suite_ticket")) {
                String SuiteTicket = (String) JsonUtil.findValueByKey(json, "SuiteTicket");
                String TimeStamp = (String) JsonUtil.findValueByKey(json, "TimeStamp");
                Qychat.setTicket(SuiteTicket);
                Qychat.setExpires_in(TimeStamp);
                QychatService.update(Qychat);
            } else if(InfoType.equals("create_auth")) {
                String AuthCode = (String) JsonUtil.findValueByKey(json, "AuthCode");
                String TimeStamp = (String) JsonUtil.findValueByKey(json, "TimeStamp");
                Qychat.setTicket(AuthCode);
                Qychat.setExpires_in(TimeStamp);
                QychatService.update(Qychat);
            }
        }
    }

    public JSONObject getJson(HttpServletRequest request, String id) throws Exception{ // OK
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(QyProperties.getToken(), QyProperties.getEncodingAESKey(), id);
        StringBuilder postData = new StringBuilder();   // 密文，对应POST请求的数据
        ServletInputStream in = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String tempStr = "";   //作为输出字符串的临时串，用于判断是否读取完毕
        String msg_signature = request.getParameter("msg_signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        while (null != (tempStr = reader.readLine())) {
            postData.append(tempStr);
        }
        System.out.println("postData" + postData);
        String xml = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, postData.toString());
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode jsonNode = xmlMapper.readTree(xml);
        return JSONObject.parseObject(jsonNode.toString());
    }

    @CrossOrigin
    @RequestMapping(value = "suiteToken") // 获取第三方应用凭证 OK
    private void getSuiteToken(String SuiteTicket) { // 获取第三方应用凭证
        JSONObject JSONObject = new JSONObject();
        JSONObject.put("suite_id", QyProperties.getSuiteID());
        JSONObject.put("suite_secret", QyProperties.getSecret());
        JSONObject.put("suite_ticket", SuiteTicket);
        String str = HttpUtil.dataPost(QyProperties.getSuite_token(), JSONObject);
        JSONObject json = JSON.parseObject(str);
        if (json != null) {
            String suite_access_token = (String) JsonUtil.findValueByKey(json, "suite_access_token");
            Integer expires_in = (Integer) JsonUtil.findValueByKey(json, "expires_in");
            Qychat Qychat = new Qychat();
            Qychat.setType("suite_access_token");
            Qychat.setTicket(suite_access_token);
            if (expires_in != null) {
                Qychat.setExpires_in(expires_in.toString());
            }
            QychatService.update(Qychat);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "preAuthCode") // 获取预授权码
    private void getPreAuthCode(String SUITE_ACCESS_TOKEN) { // 获取企业永久授权码
        JSONObject JSONObject = new JSONObject();
        String url = QyProperties.getPreAuthCode() + "?suite_access_token=" + SUITE_ACCESS_TOKEN;
        String str = HttpUtil.dataPost(url, JSONObject);
        JSONObject json = JSON.parseObject(str);
        if (json != null) {
            String pre_auth_code = (String) JsonUtil.findValueByKey(json, "pre_auth_code");
            Integer expires_in = (Integer) JsonUtil.findValueByKey(json, "expires_in");
            Qychat Qychat = new Qychat();
            Qychat.setType("pre_auth_code");
            Qychat.setTicket(pre_auth_code);
            if (expires_in != null) {
                Qychat.setExpires_in(expires_in.toString());
            }
            QychatService.update(Qychat);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "PermanentCode") // 获取企业永久授权码
    private void getPermanentCode(String SUITE_ACCESS_TOKEN, String auth_code) { // 获取企业永久授权
        JSONObject JSONObject = new JSONObject();
        JSONObject.put("auth_code", auth_code);
        String url = QyProperties.getPermanent_code() + "?suite_access_token=" + SUITE_ACCESS_TOKEN;
        String str = HttpUtil.dataPost(url, JSONObject);
        System.out.println("Permanent str:" + str);
        JSONObject json = JSON.parseObject(str);
        if (json != null) {
            String res_code = (String) JsonUtil.findValueByKey(json, "permanent_code");
            Qychat Qychat = new Qychat();
            Qychat.setType("permanent_code");
            Qychat.setTicket(res_code);
            Qychat.setExpires_in("0000");
            QychatService.update(Qychat);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "corpToken") // 获取企业凭证
    private void getCorpToken(String SUITE_ACCESS_TOKEN, String auth_code) {
        JSONObject JSONObject = new JSONObject();
        JSONObject.put("auth_corpid", QyProperties.getCorpID());
        JSONObject.put("permanent_code", auth_code);
        String url = QyProperties.getCorp_token() + "?suite_access_token=" + SUITE_ACCESS_TOKEN;
        String str = HttpUtil.dataPost(url, JSONObject);
        JSONObject json = JSON.parseObject(str);
        if (json != null) {
            String res_code = (String) JsonUtil.findValueByKey(json, "access_token");
            Integer expires_in = (Integer) JsonUtil.findValueByKey(json, "expires_in");
            Qychat Qychat = new Qychat();
            Qychat.setType("access_token");
            Qychat.setTicket(res_code);
            if (expires_in != null) {
                Qychat.setExpires_in(expires_in.toString());
            }
            QychatService.update(Qychat);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "providerToken") // 获取企业凭证 OK
    private void getProviderToken() {
        JSONObject JSONObject = new JSONObject();
        JSONObject.put("corpid", QyProperties.getCorpID());
        JSONObject.put("provider_secret", QyProperties.getProviderSecret());
        String url = QyProperties.getProvider_token();
        String str = HttpUtil.dataPost(url, JSONObject);
        JSONObject json = JSON.parseObject(str);
        if (json != null) {
            String res_code = (String) JsonUtil.findValueByKey(json, "provider_access_token");
            Integer expires_in = (Integer) JsonUtil.findValueByKey(json, "expires_in");
            Qychat Qychat = new Qychat();
            Qychat.setType("provider_access_token");
            Qychat.setTicket(res_code);
            if (expires_in != null) {
                Qychat.setExpires_in(expires_in.toString());
            }
            QychatService.update(Qychat);
        }
    }
}
