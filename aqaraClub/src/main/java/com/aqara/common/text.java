package com.aqara.common;

import com.alibaba.fastjson.JSONObject;
import com.aqara.common.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


public class text {
    static String suiteXml = "<xml><SuiteId><![CDATA[ww07fc28e86a4cb184]]></SuiteId><AuthCode><![CDATA[UYsZnScGqcxH1nxMUmdvgkgY5F-ULGbEh8CoWdnNovh3boDLLKmfNxsT6kcD2a3iRzhYYlPuCxy6auvlzlyrKzE00TmjIjumRy8ygV1_2Xw]]></AuthCode><InfoType><![CDATA[create_auth]]></InfoType><TimeStamp>1703245346</TimeStamp></xml>";

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
