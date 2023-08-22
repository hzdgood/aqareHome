package com.yunqi.common.utils;

import com.yunqi.common.properties.WxProperties;

public class WeChatUtil {
    public static String getWxToken(WxProperties wxProperties, String type) {
        String corpId = wxProperties.getCorpId();
        String corpSecret = wxProperties.getCorpSecret();
        String speedySecret = wxProperties.getSpeedySecret();
        String url = wxProperties.getHttpUrl();
        String lastUrl = "";
        if (type.equals(wxProperties.getCorpHttp())) {
            lastUrl = url + "?corpid=" + corpId + "&corpsecret=" + corpSecret;
        } else if (type.equals(wxProperties.getSpeedyHttp())) {
            lastUrl = url + "?corpid=" + corpId + "&corpsecret=" + speedySecret;
        }
        String result = "";
        try {
            result = HttpUtil.post(lastUrl);
        } catch (Exception e) {
            System.out.print("获取token over");
            e.printStackTrace();
        }
        return result;
    }

    public static String getUserId(WxProperties wxProperties, String token, String code) {
        String url = wxProperties.getUserIdUrl();
        if (token.equals("") || token == null) {
            return null;
        }
        String lastUrl = url + "?access_token=" + token + "&code=" + code;
        String result = "";
        try {
            result = HttpUtil.post(lastUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getJsApiTicket(WxProperties wxProperties, String token) {
        String url = wxProperties.getJsapiUrl();
        if (token.equals("") || token == null) {
            return null;
        }
        String lastUrl = url + "?access_token=" + token;
        String result = "";
        try {
            result = HttpUtil.post(lastUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getAppTicket(WxProperties wxProperties, String token) {
        String url = wxProperties.getAppUrl();
        if (token.equals("") || token == null) {
            return null;
        }
        String lastUrl = url + "?access_token=" + token + "&type=agent_config";
        String result = "";
        try {
            result = HttpUtil.post(lastUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getExternalContact(String userId, String token, WxProperties wxProperties) {
        String url = wxProperties.getExternalContact();
        if (token.equals("") || token == null) {
            return null;
        }
        String lastUrl = url + "?access_token=" + token + "&external_userid=" + userId;
        String result = "";
        try {
            result = HttpUtil.get(lastUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String externalList(String userId, String token, WxProperties wxProperties) {
        String url = wxProperties.getExternalList();
        if (token.equals("") || token == null) {
            return null;
        }
        String lastUrl = url + "?access_token=" + token + "&userid=" + userId;
        String result = "";
        try {
            result = HttpUtil.get(lastUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String compUser(WxProperties wxProperties, String token, String userId) {
        String url = wxProperties.getCompUser();
        if (token.equals("") || token == null) {
            return null;
        }
        String lastUrl = url + "?access_token=" + token + "&userid=" + userId;
        String result = "";
        try {
            result = HttpUtil.get(lastUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
