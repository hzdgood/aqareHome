package com.yunqi.common.utils;

public class WeChatUtil {
//    public static String getWxToken(WxProperties wxProperties, String type) {
//        String corpId = wxProperties.getCorpId();
//        String corpSecret = wxProperties.getCorpSecret();
//        String speedySecret = wxProperties.getSpeedySecret();
//        String url = wxProperties.getHttpUrl();
//        String lastUrl = "";
//        if (type.equals(wxProperties.getCorpHttp())) {
//            lastUrl = url + "?corpid=" + corpId + "&corpsecret=" + corpSecret;
//        } else if (type.equals(wxProperties.getSpeedyHttp())) {
//            lastUrl = url + "?corpid=" + corpId + "&corpsecret=" + speedySecret;
//        }
//        String result = "";
//        try {
//            result = HttpUtil.post(lastUrl);
//        } catch (Exception e) {
//            System.out.print("获取token over");
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public static JSONObject signatures(String url, String token, WxProperties WxProperties) {
//        String nonceStr = CommonUtil.getRandomString(16);
//        String timeNew = String.valueOf(System.currentTimeMillis());
//        String jsapi_ticket = WeChatUtil.getJsApiTicket(WxProperties, token);
//        String appticket = WeChatUtil.getAppTicket(WxProperties, token);
//        JSONObject jsonObject = JSONObject.parseObject(jsapi_ticket);
//        String ticket = null;
//        if (jsonObject != null) {
//            ticket = jsonObject.getString("ticket");
//        }
//        jsonObject = JSONObject.parseObject(appticket);
//        String LastAppticket = null;
//        if (jsonObject != null) {
//            LastAppticket = jsonObject.getString("ticket");
//        }
//        String str = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + timeNew + "&url=" + url;
//        String sign = DigestUtils.shaHex(str);
//        String str1 = "jsapi_ticket=" + LastAppticket + "&noncestr=" + nonceStr + "&timestamp=" + timeNew + "&url=" + url;
//        String sign1 = DigestUtils.shaHex(str1);
//        JSONObject meta = new JSONObject();
//        meta.put("nonceStr", nonceStr);
//        meta.put("timestamp", timeNew);
//        meta.put("url", url);
//        JSONObject signature = new JSONObject();
//        signature.put("signature", sign);
//        JSONObject appTicket = new JSONObject();
//        appTicket.put("signature", sign1);
//        JSONObject resObj = new JSONObject();
//        resObj.put("meta", meta);
//        resObj.put("corp", signature);
//        resObj.put("app", appTicket);
//        return resObj;
//    }
//
//    public static String getUserId(WxProperties wxProperties, String token, String code) {
//        String url = wxProperties.getUserIdUrl();
//        if (token.equals("") || token == null) {
//            return null;
//        }
//        String lastUrl = url + "?access_token=" + token + "&code=" + code;
//        String result = "";
//        try {
//            result = HttpUtil.post(lastUrl);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

//    public static String getJsApiTicket(WxProperties wxProperties, String token) {
//        String url = wxProperties.getJsapiUrl();
//        if (token.equals("") || token == null) {
//            return null;
//        }
//        String lastUrl = url + "?access_token=" + token;
//        String result = "";
//        try {
//            result = HttpUtil.post(lastUrl);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public static String getAppTicket(WxProperties wxProperties, String token) {
//        String url = wxProperties.getAppUrl();
//        if (token.equals("") || token == null) {
//            return null;
//        }
//        String lastUrl = url + "?access_token=" + token + "&type=agent_config";
//        String result = "";
//        try {
//            result = HttpUtil.post(lastUrl);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public static String getExternalContact(String userId, String token, WxProperties wxProperties) {
//        String url = wxProperties.getExternalContact();
//        if (token.equals("") || token == null) {
//            return null;
//        }
//        String lastUrl = url + "?access_token=" + token + "&external_userid=" + userId;
//        String result = "";
//        try {
//            result = HttpUtil.get(lastUrl);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public static String externalList(String userId, String token, WxProperties wxProperties) {
//        String url = wxProperties.getExternalList();
//        if (token.equals("") || token == null) {
//            return null;
//        }
//        String lastUrl = url + "?access_token=" + token + "&userid=" + userId;
//        String result = "";
//        try {
//            result = HttpUtil.get(lastUrl);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public static String compUser(WxProperties wxProperties, String token, String userId) {
//        String url = wxProperties.getCompUser();
//        if (token.equals("") || token == null) {
//            return null;
//        }
//        String lastUrl = url + "?access_token=" + token + "&userid=" + userId;
//        String result = "";
//        try {
//            result = HttpUtil.get(lastUrl);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
}
