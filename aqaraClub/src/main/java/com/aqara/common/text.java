package com.aqara.common;

import com.alibaba.fastjson.JSONObject;
import com.aqara.common.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


public class text {
    static String suiteXml = "<xml>\n" +
            "<SuiteId><![CDATA[ww07fc28e86a4cb184]]></SuiteId>\n" +
            "<InfoType><![CDATA[suite_ticket]]></InfoType>\n" +
            "<TimeStamp>1702539927</TimeStamp>\n" +
            "<SuiteTicket><![CDATA[4a6Kp0AfbUTmulPpBGA7sm3TU2_06SYdyBBS6PfbB7CIhXPpAnktfKDozvz0q-P8]]></SuiteTicket>\n" +
            "</xml>";

    public static void main(String[] args) {
        XmlMapper xml = new XmlMapper();
        {
            try {
                JsonNode jsonNode = xml.readTree(suiteXml);
                System.out.println(jsonNode.toString());
                JSONObject json = JSONObject.parseObject(jsonNode.toString());
                String SuiteTicket = (String) JsonUtil.findValueByKey(json, "SuiteTicket");
                System.out.println(SuiteTicket);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
