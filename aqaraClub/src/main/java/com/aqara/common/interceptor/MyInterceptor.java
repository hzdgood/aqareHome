package com.aqara.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.*;
import java.util.Enumeration;

@Component
public class MyInterceptor implements HandlerInterceptor {
    // 进入controller方法之前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String reqUrl = request.getRequestURI();
        Boolean status = false;
        String errorInfo = "";
        String names = "";
        System.out.printf("Interceptor:" + reqUrl);
        if (reqUrl.indexOf("getTokens") != -1){ //用户登入
            return true;
        } else if (reqUrl.indexOf("wechat") != -1 || reqUrl.indexOf("huoban") != -1 || reqUrl.indexOf("speedy") != -1){ //微信伙伴云
            return true;
        } else if (reqUrl.indexOf("error") != -1){
            return false;
        } else {
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()){
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                names = names + name + "--";
                if (name == "tokens" || name == "x-huoban-ticket") {
                    status = true;
                } else {
                    errorInfo = "Interceptor:" + reqUrl + ":" + names;
                }
            }
            if (status == false) {
                System.out.println(errorInfo);
                status = false;
            }
        }
        return status;
    }
}
