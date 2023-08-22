package com.yunqi.common.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yunqi.common.entity.Project;
import com.yunqi.common.mapper.ProjectMapper;
import com.yunqi.common.properties.HuobanProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private ProjectMapper projectMapper;
    private HuobanProperties HuobanProperties;

    @Autowired
    public void setMapper(ProjectMapper projectMapper, HuobanProperties HuobanProperties) {
        this.projectMapper = projectMapper;
        this.HuobanProperties = HuobanProperties;
    }

    public List<Project> select(String code) {
        return projectMapper.select(code);
    }

    public List<Project> currentData() {
        return projectMapper.currentData();
    }

    public void insert(Project project) {
        projectMapper.insert(project);
    }

    public void update(Project project) {
        projectMapper.update(project);
    }

    public void delete(Integer id) {
        projectMapper.delete(id);
    }

    public void deleteAll(List<Integer> ids) {
        projectMapper.deleteAll(ids);
    }

    public void deleteData() {
        projectMapper.deleteData();
    }

    public void getProjectList(String ticket, String str) throws Exception {
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
                    Project.setTask(obj2.getString("name"));
                }
                if (field_id.equals("2200000149037697")) {
                    Project.setProgress(obj2.getString("name"));
                }
                if (field_id.equals("2200000145571993")) {
                    Project.setSaleConfirm(obj2.getString("name"));
                }
                if (field_id.equals("2200000147975001")) {
                    Project.setEnd(obj2.getString("name"));
                }
                if (field_id.equals("2200000160386765")) {
                    Project.setName(obj2.getString("value"));
                }
                if (field_id.equals("2200000160386766")) {
                    Project.setTelephone(obj2.getString("value"));
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
                if (field_id.equals("2200000151011510")) {
                    Project.setAddPayReq(obj2.getString("value"));
                }
            }
            projectMapper.insert(Project);
        }
    }

    public String getSubProject(String depart, List<Project> Project) {
        String xhData = "";
        if (Project.size() == 0) {
            return "";
        }
        for (int i = 0; i < Project.size(); i++) {
            Project project = Project.get(i);
            String name = project.getName();
            String itemId = project.getItemId();
            if (Project.get(i).getDepartment() == null) {

            } else if (project.getDepartment().equals(depart)) {
                xhData += "  [" + name + "](https://app.huoban.com/tables/2100000014956047/items/" + itemId + ")"
                        + "  销售：" + project.getSales()
                        + "  需补款：" + project.getAddPayReq()
                        + "\n";
            }
        }
        String res = depart + "\n" + xhData;
        return res;
    }

    public String getCurtainData(String depart, List<Project> Project) {
        if (Project.size() == 0) {
            return "";
        }
        String xhData = "";
        int currentNumber = 0;
        for (int i = 0; i < Project.size(); i++) {
            String name = Project.get(i).getName();
            String itemId = Project.get(i).getItemId();
            if (Project.get(i).getDepartment() == null) {

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
        if (currentNumber < 10 && (currentNumber & 1) != 0) { // 奇数加/n
            xhData += " \n";
        }
        String res = depart + " 实际数量：" + currentNumber + "\n" + xhData;
        return res;
    }
}
