package com.aqara.common;

import com.alibaba.fastjson.JSONObject;
import com.aqara.common.aes.WXBizMsgCrypt;
import com.aqara.common.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.servlet.ServletInputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class text {
    public static void main(String[] args) throws Exception {
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt("9IoF",
                "hC2cKE9D2d_D4gXwpNMcSxp49T95UQz7YVSUaV4xC0DUoqwBoJ5jKhvtv8SCFEdX",
                "ww9a717b03b06063e3");
        String msg_signature = "a5f71a8e13b9d809af392e21d170b528be1ab8a6";
        String timestamp = "1703572938";
        String nonce = "1703810911";
        String postData = "<xml><ToUserName><![CDATA[ww2fd0def5ad11e2cf]]></ToUserName>" +
                "<Encrypt><![CDATA[lVTdDsSWCFcUBYSa+ggGyo/77+P1/xw3CL5DJoOcsuID1leef3GUJnmVvGLt6vEUR5n80d1wvWfk+xSmgjcvJ9OR/q4oLN4mQHbitHVWo2BGCoWFE1ikCMQGi0mkglpbjJF4IVVpd6ptoN7Tc7A8l80cbSmRbdcSn9g3riMLzTyRccQ9tnRVBfsIDCphoBV64d+r6pbi4PfrM/xoagbPHoatwHe8l2JIGIDghIWBRVGgyKLr2+991YQ9mD17l4/sGU8QjXsu7YNUqnGX9asbIh6Kf/1t8W4JLl0/iIC+t2EgMKouJMomkYc6POwwheSaUagChM2pzf+BbfZxQPCiWnpmO7AtUmErjEFlPW49Vt58flhJYu9Ht0BP6I+K2DZ1]]></Encrypt>" +
                "<AgentID><![CDATA[]]></AgentID></xml>";
//        ServletInputStream in = request.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//        while (null != (tempStr = reader.readLine())) {
//            postData.append(tempStr);
//        }
        String xml = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, postData);
        System.out.printf("xml:" + xml);
    }
}
