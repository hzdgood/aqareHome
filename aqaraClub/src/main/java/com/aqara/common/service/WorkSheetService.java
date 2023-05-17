package com.aqara.common.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.WorkSheet;
import com.aqara.common.mapper.WorkSheetMapper;
import com.aqara.common.properties.HuobanProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkSheetService {
    private WorkSheetMapper WorkSheetMapper;
    private HuobanProperties HuobanProperties;

    private static List<WorkSheet> getTechWork(List<WorkSheet> list, String engName) {
        List<WorkSheet> newList = new ArrayList<>();
        for (WorkSheet WorkSheet : list) {
            if (engName.equals(WorkSheet.getEngName())) {
                newList.add(WorkSheet);
            }
        }
        return newList;
    }

    @Autowired
    public void setMapper(WorkSheetMapper WorkSheetMapper, HuobanProperties HuobanProperties) {
        this.WorkSheetMapper = WorkSheetMapper;
        this.HuobanProperties = HuobanProperties;
    }

    public List<WorkSheet> select() {
        return WorkSheetMapper.select();
    }

    public List<WorkSheet> selectId(String code) {
        return WorkSheetMapper.selectId(code);
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

    public void deleteData() {
        WorkSheetMapper.deleteData();
    }

    public void getCurrentWork(String ticket, String selectStr) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String requestUrl = HuobanProperties.getSearchInfo() + "2100000015054992/find";
        JSONObject object = HttpService.getSchedule(requestUrl, ticket, JSONObject.parseObject(selectStr));
        JSONArray array = null;
        if (object != null) {
            array = object.getJSONArray("items");
        }
        if (array != null) {
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
                        StringBuilder title = new StringBuilder();
                        for (int k = 0; k < array2.size(); k++) {
                            JSONObject obj3 = array2.getJSONObject(k);
                            title.append(obj3.getString("title")).append(",");
                        }
                        title = new StringBuilder(title.substring(0, title.length() - 1));
                        WorkSheet.setTechnology(title.toString());
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
    }

    public String getWorkSend() {
        List<WorkSheet> list = WorkSheetMapper.select();
        List<String> users = new ArrayList<>();
        List<String> names = new ArrayList<>();
        StringBuilder str = new StringBuilder("**今日完成工单情况** \n");
        for (int i = 0; i < list.size(); i++) {
            WorkSheet WorkSheet = list.get(i);
            String engName = WorkSheet.getEngName();
            String userName = WorkSheet.getUserName();
            String count = WorkSheet.getCount();
            String restDay = WorkSheet.getRestDay();
            if (count == null || count.equals("")) {
                users.add(userName + "  " + restDay + "休  ");
            } else {
                if (!getStatus(names, engName)) {
                    List<WorkSheet> techList = getTechWork(list, engName);
                    str.append(userName).append(" ").append(restDay).append("休  ").append("完成工单数：").append(techList.size()).append("\n");
                    for (WorkSheet w : techList) {
                        String res = w.getCustom() + "-" + w.getWorkType() + "-" + w.getActualWork();
                        str.append("[").append(res).append("](https://app.huoban.com/tables/2100000015054992/items/").append(w.getItemId()).append(")").append("    ");
                    }
                    str.append("\n");
                    names.add(engName);
                }
            }
        }
        for (String user : users) {
            str.append(user).append("工单数:").append(0).append("\n");
        }
        return str.toString();
    }

    public String getWorkSendTow() {
        List<WorkSheet> list = WorkSheetMapper.selectTom();
        List<String> users = new ArrayList<>();
        List<String> names = new ArrayList<>();
        StringBuilder str = new StringBuilder("**明日工单情况** \n");
        for (int i = 0; i < list.size(); i++) {
            WorkSheet WorkSheet = list.get(i);
            String engName = WorkSheet.getEngName();
            String userName = WorkSheet.getUserName();
            String count = WorkSheet.getCount();
            String restDay = WorkSheet.getRestDay();
            if (count == null || count.equals("")) {
                users.add(userName + "  " + restDay + "休  ");
            } else {
                if (!getStatus(names, engName)) {
                    List<WorkSheet> techList = getTechWork(list, engName);
                    str.append(userName).append(" ").append(restDay).append("休  ").append("工单数：").append(techList.size()).append("\n");
                    for (WorkSheet w : techList) {
                        String res = w.getCustom() + "-" + w.getWorkType() + "-" + w.getArea();
                        str.append("[").append(res).append("](https://app.huoban.com/tables/2100000015054992/items/").append(w.getItemId()).append(")").append("    ");
                    }
                    str.append("\n");
                    names.add(engName);
                }
            }
        }
        for (String user : users) {
            str.append(user).append("工单数:").append(0).append("\n");
        }
        return str.toString();
    }

    public String getNoComplete() {
        StringBuilder str = new StringBuilder("**未预约上门工单** \n");
        List<WorkSheet> list = WorkSheetMapper.selectNoComplete();
        for (WorkSheet WorkSheet : list) {
            String custom = WorkSheet.getCustom();
            String technology = WorkSheet.getTechnology();
            str.append("客户: ").append(custom).append("   技术: ").append(technology).append("   ");
            str.append("[立即预约](https://app.huoban.com/tables/2100000015054992/items/").append(WorkSheet.getItemId()).append(")\n");
        }
        return str.toString();
    }


    private boolean getStatus(List<String> names, String engName) {
        boolean b = false;
        for (String name : names) {
            if (engName.equals(name)) {
                b = true;
                break;
            }
        }
        return b;
    }
}
