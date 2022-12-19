package com.aqara.common.utils;

import com.aqara.common.properties.ProgramProperties;

public class ProgramUtil {
    public static String getToken(ProgramProperties programProperties, String code) {
        String url = programProperties.getCode2Session();
        String Appid = programProperties.getAppid();
        String secret = programProperties.getSecret();

        String lastUrl = url + "?appid=" + Appid + "&secret=" + secret
                + "&js_code=" + code + "&grant_type=authorization_code";
        String result = HttpUtil.post(lastUrl);
        ;
        return result;
    }
}
