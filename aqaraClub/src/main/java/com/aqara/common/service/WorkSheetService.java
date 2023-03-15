package com.aqara.common.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.WorkSheet;
import com.aqara.common.properties.HuobanProperties;
import org.springframework.beans.factory.annotation.Autowired;
import com.aqara.common.mapper.WorkSheetMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkSheetService {
    @Autowired
    WorkSheetMapper WorkSheetMapper;

    @Autowired
    HuobanProperties HuobanProperties;

    public List<WorkSheet> select() {
        return WorkSheetMapper.select();
    }

    public List<WorkSheet> select1() {
        return WorkSheetMapper.select1();
    }

    public void insert(WorkSheet WorkSheet) {
        WorkSheetMapper.insert(WorkSheet);
    }

    public void update(WorkSheet WorkSheet) {
        WorkSheetMapper.update(WorkSheet);
    }

    public void delete(Integer id) {
        WorkSheetMapper.delete(id);
    }

    public void getCurrentWork(String ticket, String selectStr) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String requestUrl = HuobanProperties.getSearchInfo() + "2100000015054992/find";
        JSONObject object = HttpService.getSchedule(requestUrl, ticket, JSONObject.parseObject(selectStr));
        JSONArray array = object.getJSONArray("items");
        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            JSONArray array1 = obj.getJSONArray("fields");
            WorkSheet WorkSheet = new WorkSheet();
            WorkSheet.setItemId(obj.getString("item_id"));
            for (int j = 0; j < array1.size(); j++) {
                JSONObject obj1 = array1.getJSONObject(j);
                String field_id = obj1.getString("field_id");
                JSONArray array2 = obj1.getJSONArray("values");
                JSONObject obj2 = array2.getJSONObject(0);
                if (field_id.equals("2200000145748100")) {
                    String title = "";
                    for (int k = 0; k < array2.size(); k++) {
                        JSONObject obj3 = array2.getJSONObject(k);
                        title += obj3.getString("title") + ",";
                    }
                    title = title.substring(0, title.length() - 1);
                    WorkSheet.setTechnology(title);
                }
                if (field_id.equals("2200000146199958")) {
                    WorkSheet.setOrderDate(simpleDateFormat.parse(obj2.getString("value")));
                }
                if (field_id.equals("2200000145748099")) {
                    WorkSheet.setDateOfVisit(simpleDateFormat.parse(obj2.getString("value")));
                }
                if (field_id.equals("1101001291000000")) {
                    WorkSheet.setCustom(obj2.getString("value"));
                }
                if (field_id.equals("2200000147884674")) {
                    WorkSheet.setActualWork(obj2.getString("value"));
                }
                if (field_id.equals("2200000146398522")) {
                    WorkSheet.setCompleteTime(simpleDateFormat.parse(obj2.getString("value")));
                }
                if (field_id.equals("2200000146398516")) {
                    WorkSheet.setWorkType(obj2.getString("name"));
                }
                if (field_id.equals("1101001257000000")) {
                    WorkSheet.setArea(obj2.getString("title"));
                }
                WorkSheet.setCreateName("上海汇社");
            }
            WorkSheetMapper.insert(WorkSheet);
        }

    }

    public String getWorkSend() {
        List<WorkSheet> list = formatList(WorkSheetMapper.select());
        String str = "**今日完成工单情况** \n";
        return getSendStr(list, str);
    }

    public String getWorkSend1() {
        List<WorkSheet> list = formatList(WorkSheetMapper.select1());
        String str = "**明日工单情况** \n";
        return getSendStr1(list, str);
    }

    private String getSendStr(List<WorkSheet> list, String str) {
        List<String> names = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            WorkSheet WorkSheet = list.get(i);
            String technology = WorkSheet.getTechnology();
            if (!getStatus(names, technology)) {
                List<WorkSheet> current = getTechWork(list, technology);
                if (current.size() != 0) {
                    str += technology + "   完成工单数:" + current.size() + "\n";
                    for (int j = 0; j < current.size(); j++) {
                        WorkSheet w = current.get(j);
                        String res = w.getCustom() + "-" + w.getWorkType() + "-" + w.getActualWork();
                        str += "[" + res + "](https://app.huoban.com/tables/2100000015054992/items/" + w.getItemId() + ")" + "    ";
                    }
                    str += "\n";
                }
                names.add(technology);
            }
        }
        return str;
    }

    private String getSendStr1(List<WorkSheet> list, String str) {
        List<String> names = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            WorkSheet WorkSheet = list.get(i);
            String technology = WorkSheet.getTechnology();
            if (!getStatus(names, technology)) {
                List<WorkSheet> current = getTechWork(list, technology);
                if (current.size() != 0) {
                    str += technology + "   工单数:" + current.size() + "\n";
                    for (int j = 0; j < current.size(); j++) {
                        WorkSheet w = current.get(j);
                        String res = w.getCustom() + "-" + w.getWorkType() + "-" + w.getArea();
                        str += "[" + res + "](https://app.huoban.com/tables/2100000015054992/items/" + w.getItemId() + ")" + "    ";
                    }
                    str += "\n";
                }
                names.add(technology);
            }
        }
        return str;
    }

    private boolean getStatus(List<String> names, String technology) {
        boolean b = false;
        for (int i = 0; i < names.size(); i++) {
            if (technology.equals(names.get(i))) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 获取技术人员List
     */
    private static List<WorkSheet> getTechWork(List<WorkSheet> list, String technology) {
        List<WorkSheet> newList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            WorkSheet WorkSheet = list.get(i);
            if (technology.equals(WorkSheet.getTechnology())) {
                newList.add(WorkSheet);
            }
        }
        return newList;
    }

    /**
     * 数据重组，技术人员分开
     */
    private static List<WorkSheet> formatList(List<WorkSheet> list) {
        List<WorkSheet> newList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            WorkSheet WorkSheet = list.get(i);
            String technology = WorkSheet.getTechnology();
            if (!technology.equals("") && technology != null) {
                String[] str = technology.split(",");
                for (int j = 0; j < str.length; j++) {
                    WorkSheet w = new WorkSheet();
                    w.setWorkType(WorkSheet.getWorkType());
                    w.setActualWork(WorkSheet.getActualWork());
                    w.setCustom(WorkSheet.getCustom());
                    w.setItemId(WorkSheet.getItemId());
                    w.setArea(WorkSheet.getArea());
                    w.setTechnology(str[j]);
                    newList.add(w);
                }
            }
        }
        return newList;
    }
}
