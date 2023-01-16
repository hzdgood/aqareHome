package com.aqara.common.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.Project;
import com.aqara.common.mapper.ProjectMapper;
import com.aqara.common.properties.HuobanProperties;
import com.aqara.common.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    HuobanProperties HuobanProperties;

    public List<Project> select(String code) {
        return projectMapper.select(code);
    }

    public List<Project> currentData() {
        return projectMapper.currentData();
    }

    public void insert(Project project) {
        projectMapper.insert(project);
    }

    public void delete(Integer id) {
        projectMapper.delete(id);
    }

    public void deleteAll(List<Integer> ids) {
        projectMapper.deleteAll(ids);
    }

    public void getProjectList(String ticket) throws Exception {
        String str = CommonUtil.getProjectData();
        String requestUrl = HuobanProperties.getSearchInfo() + "2100000014956047/find";
        JSONObject object = HttpService.getSchedule(requestUrl, ticket, JSONObject.parseObject(str));
        JSONArray array = object.getJSONArray("items");
        for (int i = 0; i < array.size(); i++) {
            Project Project = new Project();
            JSONObject obj = array.getJSONObject(i);
            JSONArray array1 = obj.getJSONArray("fields");
            Project.setProjectType("全屋");
            Project.setItemId(obj.getString("item_id"));
            for (int j = 0; j < array1.size(); j++) {
                JSONObject obj1 = array1.getJSONObject(j);
                String field_id = obj1.getString("field_id");
                JSONArray array2 = obj1.getJSONArray("values");
                JSONObject obj2 = array2.getJSONObject(0);
                if (field_id.equals("2200000150460774")) {
                    Project.setCode(obj2.getString("value"));
                }
                if (field_id.equals("2200000154568276")) {
                    Project.setCurrrentTask(obj2.getString("name"));
                }
                if (field_id.equals("2200000149037697")) {
                    Project.setDecorationProgress(obj2.getString("name"));
                }
                if (field_id.equals("2200000145571993")) {
                    Project.setSaleConfirm(obj2.getString("name"));
                }
                if (field_id.equals("2200000147975001")) {
                    Project.setEnd(obj2.getString("name"));
                }
                if (field_id.equals("1101001102000000")) {
                    Project.setProjectName(obj2.getString("value"));
                }
                if (field_id.equals("1101001103000000")) {
                    Project.setProjectTelephone(obj2.getString("value"));
                }
                if (field_id.equals("2200000184791041")) {
                    Project.setMainProject(obj2.getString("name"));
                }
                if (field_id.equals("1117001102000000")) {
                    Project.setSales(obj2.getString("name"));
                }
                if (field_id.equals("1294001101000000")) {
                    Project.setDepartment(obj2.getString("value"));
                }

            }
            projectMapper.insert(Project);
        }
    }

    public String getCurtainData(String depart, List<Project> Project) {
        if (Project.size() == 0) {
            return "";
        }
        String xhData = "";
        int currentNumber = 0;
        for (int i = 0; i < Project.size(); i++) {
            String name = Project.get(i).getProjectName();
            String itemId = Project.get(i).getItemId();
            if (Project.get(i).getDepartment() == null || Project.get(i).getDepartment().equals(null)) {

            } else if (Project.get(i).getDepartment().equals(depart)) {
                if (currentNumber < 10) {
                    if (currentNumber % 2 == 0) {
                        xhData += ">" + "[" + name + "](https://app.huoban.com/tables/2100000014956047/items/" + itemId + ")";
                    } else {
                        xhData += "   [" + name + "](https://app.huoban.com/tables/2100000014956047/items/" + itemId + ")" + "\n";
                    }
                }
                currentNumber = currentNumber + 1;
            }
        }
        if (currentNumber % 3 == 0) {
            xhData += " \n";
        }
        String res = depart + " 实际数量：" + currentNumber + "\n" + xhData;
        return res;
    }
}
