package com.aqara.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.Wechat;
import com.aqara.common.properties.WxProperties;
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
import java.util.List;

@RestController
@RequestMapping("/wechat")
public class WechatController {
    private WxProperties WxProperties;
    private WechatService WechatService;

    @Autowired
    public void setMapper(WxProperties WxProperties, WechatService WechatService) {
        this.WechatService = WechatService;
        this.WxProperties = WxProperties;
    }

    /**
     * 获取企业微信token
     */
    @RequestMapping("/getToken")
    public String getToken(@RequestBody String type) {
        String token = "";
        List<Wechat> list = WechatService.select(type);
        if (list.isEmpty()) {
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
        if (jsonObject == null) {
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
        if (token.isEmpty()) {
            return null;
        }
        String res = WeChatUtil.getUserId(WxProperties, token, code);
        JSONObject jsonObject = JSONObject.parseObject(res);
        if (jsonObject != null) {
            return jsonObject.getString("UserId");
        }
        return null;
    }

    @CrossOrigin
    @RequestMapping("/compUser")
    public JSONObject compUser(String userId, String type) {
        String token = getToken(type);
        if (token.isEmpty()) {
            return null;
        }
        String res = WeChatUtil.compUser(WxProperties, token, userId);
        return JSONObject.parseObject(res);
    }

    /**
     * JS-JSD注册
     */
    @CrossOrigin
    @RequestMapping("/signatures")
    public JSONObject signatures(String url, String type) {
        String token = getToken(type);
        if (token.isEmpty()) {
            return null;
        }
        return CommonUtil.signatures(url, token, WxProperties);
    }

    /**
     * 获取外部联系人详细信息
     */
    @CrossOrigin
    @RequestMapping("/externalContact")
    public String externalContact(String userId, String type) {
        String token = getToken(type);
        if (token.isEmpty()) {
            return null;
        }
        return WeChatUtil.getExternalContact(userId, token, WxProperties);
    }

    /**
     * 获取外部联系人详细信息
     */
    @CrossOrigin
    @RequestMapping("/externalList")
    public String externalList(String userId, String type) {
        String token = getToken(type);
        if (token.isEmpty()) {
            return null;
        }
        return WeChatUtil.externalList(userId, token, WxProperties);
    }

    /**
     * 获取外部联系人详细信息
     */
    @CrossOrigin
    @RequestMapping("/groupChat")
    public String groupChat(String chatId, String type) {
        String token = getToken(type);
        if (token.isEmpty()) {
            return null;
        }
        String userInfo = WxProperties.getGroupChat() + "?access_token=" + token;
        JSONObject obj = new JSONObject();
        obj.put("chat_id", chatId);
        return HttpUtil.dataPost(userInfo, obj);
    }

    /**
     * 获取外部联系人详细信息
     */
    @CrossOrigin
    @RequestMapping("/groupList")
    public String groupList(String chatId, String type) {
        String token = getToken(type);
        if (token.isEmpty()) {
            return null;
        }
        String userInfo = WxProperties.getGroupList() + "?access_token=" + token;
        JSONObject obj = new JSONObject();
        obj.put("chat_id", chatId);
        return HttpUtil.dataPost(userInfo, obj);
    }

    /**
     * 上传文件
     */
    @CrossOrigin
    @RequestMapping("/mediaUpload")
    public String mediaUpload(String fileName, String type) throws IOException {
        String token = getToken(type);
        if (token.isEmpty()) {
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
//    @CrossOrigin
//    @RequestMapping("/schedule/add")
//    public void schedule(Schedule Schedule) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String userId = Schedule.getUserid();
//        String attendees = "";
//        userId = userId.replaceAll(" ", "");
//        if (userId.contains("、")) {
//            String[] users = userId.split("、");
//            for (String user : users) {
//                List<User> userlist = UserService.select(user, "");
//                if (!userlist.isEmpty()) {
//                    User User = userlist.get(0);
//                    attendees += "{\"userid\": \"" + User.getEngName() + "\"},";
//                }
//            }
//        } else {
//            List<User> userlist = UserService.select(userId, "");
//            if (!userlist.isEmpty()) {
//                User User = userlist.get(0);
//                attendees += "{\"userid\": \"" + User.getEngName() + "\"},";
//            }
//        }
//        attendees = attendees.substring(0, attendees.length() - 1);
//        try {
//            Date date = sdf.parse(Schedule.getStartTime());
//            long StartTime = date.getTime() / 1000;
//            int adds = Integer.parseInt(Schedule.getDuration()) * 3600;
//            long endTime = date.getTime() / 1000 + adds;
//            String token = getToken(CommonProperties.getServiceUrl());
//            String userInfo = WxProperties.getScheduleAdd() + "?access_token=" + token;
//            String str = "{\"schedule\": {"
//                    + "\"start_time\": " + StartTime + ","
//                    + "\"end_time\": " + endTime + ","
//                    + "\"summary\": \"" + Schedule.getSummary() + "\","
//                    + "\"description\": \"" + Schedule.getDescription() + "\","
//                    + "\"location\": \"" + Schedule.getLocation() + "\","
//                    + "\"attendees\": [" + attendees + "]}}";
//            HttpUtil.scheduleReq(userInfo, str);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
