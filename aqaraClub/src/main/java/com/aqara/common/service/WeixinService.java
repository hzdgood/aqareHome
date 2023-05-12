package com.aqara.common.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.Weixin;
import com.aqara.common.mapper.WeixinMapper;
import com.aqara.common.properties.HuobanProperties;
import com.aqara.common.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeixinService {
    private WeixinMapper WeixinMapper;
    private HuobanProperties HuobanProperties;

    @Autowired
    public void setMapper(WeixinMapper WeixinMapper, HuobanProperties HuobanProperties) {
        this.WeixinMapper = WeixinMapper;
        this.HuobanProperties = HuobanProperties;
    }

    public List<Weixin> select() {
        return WeixinMapper.select();
    }

    public List<Weixin> selectAll() {
        return WeixinMapper.selectAll();
    }

    public void insert(Weixin Weixin) {
        WeixinMapper.insert(Weixin);
    }

    public void delete(Integer id) {
        WeixinMapper.delete(id);
    }

    public void update(Weixin weixin) {
        WeixinMapper.update(weixin);
    }

    public void getWeixinData(String ticket) {
        String str = CommonUtil.getToday();
        String requestUrl = HuobanProperties.getSearchInfo() + "2100000019533688/find";
        JSONObject object = HttpService.getSchedule(requestUrl, ticket, JSONObject.parseObject(str));
        JSONArray array = object.getJSONArray("items");
        for (int i = 0; i < array.size(); i++) {
            Weixin Weixin = new Weixin();
            Weixin.setCreateName("企业微信");
            JSONObject obj = array.getJSONObject(i);
            JSONArray array1 = obj.getJSONArray("fields");
            for (int j = 0; j < array1.size(); j++) {
                JSONObject obj1 = array1.getJSONObject(j);
                String field_id = obj1.getString("field_id");
                JSONArray array2 = obj1.getJSONArray("values");
                JSONObject obj2 = array2.getJSONObject(0);
                if (field_id.equals("2200000182047716")) {
                    Weixin.setName(obj2.getString("value"));
                } else if (field_id.equals("2200000182047718")) {
                    Weixin.setType(obj2.getString("value"));
                } else if (field_id.equals("2200000182047720")) {
                    Weixin.setSales(obj2.getString("value"));
                } else if (field_id.equals("2200000182047724")) {
                    Weixin.setUuid(obj2.getString("value"));
                }
            }
            WeixinMapper.insert(Weixin);
        }
    }
}

