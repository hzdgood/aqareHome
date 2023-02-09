package com.aqara.common.interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.*;

@Component
public class MyInterceptor implements HandlerInterceptor {
    // 进入controller方法之前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tokens = request.getHeader("tokens");
        String huoBanTicket = request.getHeader("X-Huoban-Ticket");
        String reqUrl = request.getRequestURI();
        StringUtils StrUtils = new StringUtils();
        if(reqUrl.indexOf("getTokens") != -1){
            System.out.println("getTokens:" + reqUrl);
        } else if(StrUtils.isNotBlank(tokens) || StrUtils.isNotBlank(huoBanTicket)){
            System.out.println("postHandler:" + reqUrl);
        } else {
            System.out.println("Interceptor:" + reqUrl);
        }
        return true;
    }
}
