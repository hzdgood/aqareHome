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

    @CrossOrigin // 开发
    @RequestMapping(value = "login", method = RequestMethod.GET) // 测试成功
    public void getlogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
    @RequestMapping(value = "login", method = RequestMethod.POST) // 企业微信回调
    public void postLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String type = request.getParameter("type");
        System.out.println("type:" + type);
        PrintWriter out = response.getWriter();
        out.print("success");
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
    @RequestMapping(value = "receive", method = RequestMethod.POST) // 企业微信回调
    public void doPostValid(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String CORPID = request.getParameter("CORPID");
        String id = "";
        System.out.println("CORPID:" + CORPID);
        if(CORPID != null){
            id = CORPID; // 测试企业CorpId
        } else {
            id = QyProperties.getSuiteID();
        }
        JSONObject json = getJson(request, id);
        String InfoType = (String) JsonUtil.findValueByKey(json, "InfoType");
        Qychat Qychat = new Qychat();
        Qychat.setType(InfoType);
        System.out.println("InfoType:" + InfoType);
        if(InfoType != null) {
            if(InfoType.equals("suite_ticket")) {
                String SuiteTicket = (String) JsonUtil.findValueByKey(json, "SuiteTicket");
                String TimeStamp = (String) JsonUtil.findValueByKey(json, "TimeStamp");
                Qychat.setTicket(SuiteTicket);
                Qychat.setExpires_in(TimeStamp);
                getSuiteToken(SuiteTicket);
            } else if(InfoType.equals("create_auth")) {
                String AuthCode = (String) JsonUtil.findValueByKey(json, "create_auth");
                String TimeStamp = (String) JsonUtil.findValueByKey(json, "TimeStamp");
                Qychat.setTicket(AuthCode);
                Qychat.setExpires_in(TimeStamp);
             } else if(InfoType.equals("change_auth")) {
                String AuthCode = (String) JsonUtil.findValueByKey(json, "change_auth");
                String TimeStamp = (String) JsonUtil.findValueByKey(json, "TimeStamp");
                Qychat.setTicket(AuthCode);
                Qychat.setExpires_in(TimeStamp);
            }
            QychatService.insert(Qychat);
        }
        PrintWriter out = response.getWriter();
        out.print("success");
    }

    public JSONObject getJson(HttpServletRequest request, String id) throws Exception{
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
        String xml = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, postData.toString());
        System.out.printf("xml:" + xml);
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode jsonNode = xmlMapper.readTree(xml);
        return JSONObject.parseObject(jsonNode.toString());
    }

    private void getSuiteToken(String SuiteTicket) { // 获取第三方应用凭证
        JSONObject JSONObject = new JSONObject();
        JSONObject.put("suite_id", QyProperties.getSuiteID());
        JSONObject.put("suite_secret", QyProperties.getSecret());
        JSONObject.put("suite_ticket", SuiteTicket);
        String str = HttpUtil.dataPost(QyProperties.getSuite_token(), JSONObject);
        System.out.printf("SuiteToken:" + str);
        JSONObject json = JSON.parseObject(str);
        if (json != null) {
            String suite_access_token = (String) JsonUtil.findValueByKey(json, "suite_access_token");
            String expires_in = (String) JsonUtil.findValueByKey(json, "expires_in");
            Qychat Qychat = new Qychat();
            Qychat.setType("suite_access_token");
            Qychat.setTicket(suite_access_token);
            Qychat.setExpires_in(expires_in);
            QychatService.insert(Qychat);
        }
    }

    private void getPreAuthCode(String SUITE_ACCESS_TOKEN) { // 获取企业永久授权码
        JSONObject JSONObject = new JSONObject();
        String url = QyProperties.getPreAuthCode() + "?suite_access_token=" + SUITE_ACCESS_TOKEN;
        String str = HttpUtil.dataPost(url, JSONObject);
        JSONObject json = JSON.parseObject(str);
        if (json != null) {
            String pre_auth_code = (String) JsonUtil.findValueByKey(json, "pre_auth_code");
            String expires_in = (String) JsonUtil.findValueByKey(json, "expires_in");
            Qychat Qychat = new Qychat();
            Qychat.setType("pre_auth_code");
            Qychat.setTicket(pre_auth_code);
            Qychat.setExpires_in(expires_in);
            QychatService.insert(Qychat);
            // getPermanentCode(SUITE_ACCESS_TOKEN, pre_auth_code);
        }
    }

    private void getPermanentCode(String SUITE_ACCESS_TOKEN, String auth_code) { // 获取企业永久授权码
        JSONObject JSONObject = new JSONObject();
        JSONObject.put("auth_code", auth_code);
        String url = QyProperties.getPermanent_code() + "?suite_access_token=" + SUITE_ACCESS_TOKEN;
        String str = HttpUtil.dataPost(url, JSONObject);
        JSONObject json = JSON.parseObject(str);
        if (json != null) {
            String res_code = (String) JsonUtil.findValueByKey(json, "permanent_code");
            Qychat Qychat = new Qychat();
            Qychat.setType("permanent_code");
            Qychat.setTicket(res_code);
            Qychat.setExpires_in("0000");
            QychatService.insert(Qychat);
        }
    }

    private void getCorpToken(String SUITE_ACCESS_TOKEN, String auth_code) { // 获取企业永久授权码
        JSONObject JSONObject = new JSONObject();
        JSONObject.put("auth_corpid", QyProperties.getCorpID());
        JSONObject.put("permanent_code", auth_code);
        String url = QyProperties.getCorp_token() + "?suite_access_token=" + SUITE_ACCESS_TOKEN;
        String str = HttpUtil.dataPost(url, JSONObject);
        JSONObject json = JSON.parseObject(str);
        if (json != null) {
            String res_code = (String) JsonUtil.findValueByKey(json, "access_token");
            String expires_in = (String) JsonUtil.findValueByKey(json, "expires_in");
            Qychat Qychat = new Qychat();
            Qychat.setType("access_token");
            Qychat.setTicket(res_code);
            Qychat.setExpires_in(expires_in);
            QychatService.insert(Qychat);
        }
    }
}
