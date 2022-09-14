package com.aqara.common.service;

import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.Project;
import com.aqara.common.mapper.ProjectMapper;
import com.aqara.common.properties.HuobanProperties;
import com.aqara.common.utils.CommonUtil;

@Service
public class ProjectService {
	
	@Autowired
	ProjectMapper projectMapper;
	
	@Autowired
	HuobanProperties HuobanProperties;
	
	public List<Project> select(String code){
		return projectMapper.select(code);
	}
	
	public void insert(Project project) {
		projectMapper.insert(project);
	}
	
	public void upload(Project project) {
		projectMapper.upload(project);
	}
	
	public void delete(Integer id) {
		projectMapper.delete(id);
	}
	
	public void getProjectList(String ticket) throws Exception{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = CommonUtil.getProjectData();
		String requestUrl = HuobanProperties.getSearchInfo() + "2100000014956047/find";
		JSONObject object = HttpService.getSchedule(requestUrl, ticket, JSONObject.parseObject(str));
		JSONArray array = object.getJSONArray("items");
		for (int i = 0; i < array.size(); i++) {
			Project Project = new Project();
			JSONObject obj = array.getJSONObject(i);
			JSONArray array1 = obj.getJSONArray("fields");
			Project.setCreateTime(simpleDateFormat.parse(obj.getString("created_on")));
			Project.setUpdateTime(simpleDateFormat.parse(obj.getString("updated_on")));
			Project.setProjectType("全屋");
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
				if (field_id.equals("1117001107000000")) {
					Project.setDepartment(obj2.getString("title"));
				}
				
			}
			projectMapper.upload(Project);
		}
	}
	
	public String getCurtainData() {
		String str = "**开合帘5天未发货** \n";
		
		return str;
	}
}
