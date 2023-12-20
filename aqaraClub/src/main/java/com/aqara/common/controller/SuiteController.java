package com.aqara.common.controller;

import com.alibaba.fastjson.JSON;
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

    @CrossOrigin
    @RequestMapping(value = "receive", method = RequestMethod.GET) // 测试成功
    public void doGetValid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg_signature = request.getParameter("msg_signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(QyProperties.getToken(), QyProperties.getEncodingAESKey(), QyProperties.getCorpID());
        String sEchoStr = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
        PrintWriter out = response.getWriter();
        out.print(sEchoStr);
    }

    @CrossOrigin
    @RequestMapping(value = "receive", method = RequestMethod.POST) // 企业微信回调
    public void doPostValid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg_signature = request.getParameter("msg_signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String type = request.getParameter("type");
        String id = "";
        if(type != null && type.equals("data")){
            id = QyProperties.getCorpID();
        } else {
            id = QyProperties.getSuiteID();
        }
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(QyProperties.getToken(), QyProperties.getEncodingAESKey(), id);
        StringBuilder postData = new StringBuilder();   // 密文，对应POST请求的数据
        ServletInputStream in = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String tempStr = "";   //作为输出字符串的临时串，用于判断是否读取完毕
        while (null != (tempStr = reader.readLine())) {
            postData.append(tempStr);
        }
        String xml = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, postData.toString());
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode jsonNode = xmlMapper.readTree(xml);
        JSONObject json = JSONObject.parseObject(jsonNode.toString());
        String InfoType = (String) JsonUtil.findValueByKey(json, "InfoType");
        Qychat Qychat = new Qychat();
        Qychat.setType(InfoType);
        if(InfoType != null) {
            if(InfoType.equals("suite_ticket")) {
                String SuiteTicket = (String) JsonUtil.findValueByKey(json, "SuiteTicket");
                String TimeStamp = (String) JsonUtil.findValueByKey(json, "TimeStamp");
                Qychat.setTicket(SuiteTicket);
                Qychat.setExpires_in(TimeStamp);
                getSuiteTicket(SuiteTicket);
            } else {
                String AuthCode = (String) JsonUtil.findValueByKey(json, "AuthCode");
                String TimeStamp = (String) JsonUtil.findValueByKey(json, "TimeStamp");
                Qychat.setTicket(AuthCode);
                Qychat.setExpires_in(TimeStamp);
            }
        }
        QychatService.insert(Qychat);
        PrintWriter out = response.getWriter();
        out.print("success");
    }

    private void getSuiteTicket(String SuiteTicket) { // 获取第三方应用凭证
        JSONObject JSONObject = new JSONObject();
        JSONObject.put("suite_id", QyProperties.getSuiteID());
        JSONObject.put("suite_secret", QyProperties.getSecret());
        JSONObject.put("suite_ticket", SuiteTicket);
        String str = HttpUtil.dataPost(QyProperties.getSuite_token(), JSONObject);
        JSONObject json = JSON.parseObject(str);
        if (json != null) {
            String suite_access_token = (String) JsonUtil.findValueByKey(json, "suite_access_token");
            String expires_in = (String) JsonUtil.findValueByKey(json, "expires_in");
            Qychat Qychat = new Qychat();
            Qychat.setType("SuiteTicket");
            Qychat.setTicket(suite_access_token);
            Qychat.setExpires_in(expires_in);
            QychatService.insert(Qychat);
        }
    }

    private void getPermanentCode(String SUITE_ACCESS_TOKEN, String auth_code) { // 获取企业永久授权码
        JSONObject JSONObject = new JSONObject();
        JSONObject.put("auth_code", auth_code);
        String url = QyProperties.getPermanent_code() + "?suite_access_token=" + SUITE_ACCESS_TOKEN;
        String str = HttpUtil.dataPost(url, JSONObject);
        JSONObject json = JSON.parseObject(str);
        if (json != null) {
            String res_code = (String) JsonUtil.findValueByKey(json, "auth_code");
            Qychat Qychat = new Qychat();
            Qychat.setType("res_code");
            Qychat.setTicket(res_code);
            Qychat.setExpires_in("0000");
            QychatService.insert(Qychat);
        }
    }
}
