package com.yunqi.common.controller;

import com.yunqi.common.view.*;
import com.yunqi.common.viewService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/view")
public class ViewController {
    private ProjectViewService ProjectViewService;
    private WorkViewService WorkViewService;
    private SchemeViewService SchemeViewService;
    private WriterViewService WriterViewService;
    @Autowired
    public void setMapper(ProjectViewService ProjectViewService) {
        this.ProjectViewService = ProjectViewService;
    }
    @Autowired
    public void setMapper(WorkViewService WorkViewService) {
        this.WorkViewService = WorkViewService;
    }
    @Autowired
    public void setMapper(SchemeViewService SchemeViewService) {
        this.SchemeViewService = SchemeViewService;
    }
    @Autowired
    public void setMapper(WriterViewService WriterViewService) {
        this.WriterViewService = WriterViewService;
    }
    @CrossOrigin
    @RequestMapping("/project")
    public List<ProjectView> select(ProjectView ProjectView) {
        return ProjectViewService.select(ProjectView);
    }
    @CrossOrigin
    @RequestMapping("/work")
    public List<WorkView> select(WorkView WorkView) throws ParseException {
        String techIds = WorkView.getTechIds();
        if(techIds.equals("1")) {
            WorkView.setTechIds(null);
        }
        return WorkViewService.select(WorkView);
    }
    @CrossOrigin
    @RequestMapping("/scheme")
    public List<SchemeView> select(SchemeView SchemeView) {
        return SchemeViewService.select(SchemeView);
    }
    @CrossOrigin
    @RequestMapping("/writer")
    public List<WriterView> select(WriterView WriterView) {
        return WriterViewService.select(WriterView);
    }
}
