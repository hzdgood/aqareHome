package com.aqara.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.Schedule;
import com.aqara.common.entity.User;
import com.aqara.common.entity.Wechat;
import com.aqara.common.properties.CommonProperties;
import com.aqara.common.properties.WxProperties;
import com.aqara.common.service.UserService;
import com.aqara.common.service.WechatService;
import com.aqara.common.utils.CommonUtil;
import com.aqara.common.utils.HttpUtil;
import com.aqara.common.utils.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/wechat")
public class WechatController {
    @Autowired
    CommonProperties CommonProperties;

    @Autowired
    WxProperties WxProperties;

    @Autowired
    WechatService WechatService;

    @Autowired
    UserService UserService;

    /**
     * 获取企业微信token
     */
    @RequestMapping("/getToken")
    public String getToken(@RequestBody String type) {
        String token = "";
        List<Wechat> list = WechatService.select(type);
        if (list.size() == 0) {
            return insert(type);
        }
        Wechat Wechat = list.get(list.size() - 1);
        long expired = Long.parseLong(Wechat.getExpired()) * 800; // 有效时间
        long date = Wechat.getDate().getTime(); // 凭证开始时间
        long sys = System.currentTimeMillis(); // 当前
        if (expired + date <= sys) { //是否过期
            return insert(type);
        } else {
            token = Wechat.getTicket();
        }
        return token;
    }

    synchronized private String insert(String type) {
        String res = WeChatUtil.getWxToken(WxProperties, type);
        JSONObject jsonObject = JSONObject.parseObject(res);
        if(jsonObject == null) {
            return null;
        }
        Wechat Wechat = new Wechat();
        Wechat.setTicket(jsonObject.getString("access_token"));
        Wechat.setType(type);
        Wechat.setExpired(jsonObject.getString("expires_in"));
        WechatService.insert(Wechat);
        return jsonObject.getString("access_token");
    }

    /**
     * 获取当前用户
     */
    @CrossOrigin
    @RequestMapping("/getUserId")
    public String getUserId(String code, String type) {
        String token = getToken(type);
        if(token == null || token.equals("")){
            return null;
        }
        String res = WeChatUtil.getUserId(WxProperties, token, code);
        JSONObject jsonObject = JSONObject.parseObject(res);
        String UserId = jsonObject.getString("UserId");
        return UserId;
    }

    @CrossOrigin
    @RequestMapping("/compUser")
    public JSONObject compUser(String userId, String type) {
        String token = getToken(type);
        if(token == null || token.equals("")){
            return null;
        }
        String res = WeChatUtil.compUser(WxProperties, token, userId);
        JSONObject jsonObject = JSONObject.parseObject(res);
        return jsonObject;
    }

    /**
     * JS-JSD注册
     */
    @CrossOrigin
    @RequestMapping("/signatures")
    public JSONObject signatures(String url, String type) {
        String token = getToken(type);
        if(token == null || token.equals("")){
            return null;
        }
        JSONObject jsonObject = CommonUtil.signatures(url, token, WxProperties);
        return jsonObject;
    }

    /**
     * 获取外部联系人详细信息
     */
    @CrossOrigin
    @RequestMapping("/externalContact")
    public String externalContact(String userId, String type) {
        String token = getToken(type);
        if(token == null || token.equals("")){
            return null;
        }
        String res = WeChatUtil.getExternalContact(userId, token, WxProperties);
        return res;
    }

    /**
     * 获取外部联系人详细信息
     */
    @CrossOrigin
    @RequestMapping("/externalList")
    public String externalList(String userId, String type) {
        String token = getToken(type);
        if(token == null || token.equals("")){
            return null;
        }
        String res = WeChatUtil.externalList(userId, token, WxProperties);
        return res;
    }

    /**
     * 获取外部联系人详细信息
     */
    @CrossOrigin
    @RequestMapping("/groupChat")
    public String groupChat(String chatId, String type) {
        String token = getToken(type);
        if(token == null || token.equals("")){
            return null;
        }
        String userInfo = WxProperties.getGroupChat() + "?access_token=" + token;
        JSONObject obj = new JSONObject();
        obj.put("chat_id", chatId);
        String res = HttpUtil.dataPost(userInfo, obj);
        return res;
    }

    /**
     * 获取外部联系人详细信息
     */
    @CrossOrigin
    @RequestMapping("/groupList")
    public String groupList(String chatId, String type) {
        String token = getToken(type);
        if(token == null || token.equals("")){
            return null;
        }
        String userInfo = WxProperties.getGroupList() + "?access_token=" + token;
        JSONObject obj = new JSONObject();
        obj.put("chat_id", chatId);
        String res = HttpUtil.dataPost(userInfo, obj);
        return res;
    }

    /**
     * 上传文件
     */
    @CrossOrigin
    @RequestMapping("/mediaUpload")
    public String mediaUpload(String fileName, String type) throws IOException {
        String token = getToken(type);
        if(token == null || token.equals("")){
            return null;
        }
        String mediaUrl = WxProperties.getMediaUpload() + "?access_token=" + token + "&type=file";
        String res = "";
        if (fileName.contains(".jpg") || fileName.contains(".png")) {
            res = HttpUtil.uploadImg(fileName, token);
        } else {
            res = HttpUtil.mediaPost(mediaUrl, fileName);
        }
        return res;
    }

    /*
     * 工勘链接企业微信日程接口 *****
     * */
    @CrossOrigin
    @RequestMapping("/schedule/add")
    public void schedule(Schedule Schedule) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String userId = Schedule.getUserid();
        String attendees = "";
        userId = userId.replaceAll(" ", "");
        if (userId.contains("、")) {
            String[] users = userId.split("、");
            for (int i = 0; i < users.length; i++) {
                List<User> userlist = UserService.select(users[i], "");
                if (userlist.size() > 0) {
                    User User = userlist.get(0);
                    attendees += "{\"userid\": \"" + User.getEngName() + "\"},";
                }
            }
        } else {
            List<User> userlist = UserService.select(userId, "");
            if (userlist.size() > 0) {
                User User = userlist.get(0);
                attendees += "{\"userid\": \"" + User.getEngName() + "\"},";
            }
        }
        attendees = attendees.substring(0, attendees.length() - 1);
        try {
            Date date = sdf.parse(Schedule.getStartTime());
            Long StartTime = date.getTime() / 1000;
            int adds = Integer.parseInt(Schedule.getDuration()) * 3600;
            Long endTime = date.getTime() / 1000 + adds;
            String token = getToken(CommonProperties.getServiceUrl());
            String userInfo = WxProperties.getScheduleAdd() + "?access_token=" + token;
            String str = "{\"schedule\": {"
                    + "\"start_time\": " + StartTime + ","
                    + "\"end_time\": " + endTime + ","
                    + "\"summary\": \"" + Schedule.getSummary() + "\","
                    + "\"description\": \"" + Schedule.getDescription() + "\","
                    + "\"location\": \"" + Schedule.getLocation() + "\","
                    + "\"attendees\": [" + attendees + "]}}";
            HttpUtil.scheduleReq(userInfo, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
