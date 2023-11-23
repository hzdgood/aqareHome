package com.yunqi.common.controller;

import com.yunqi.common.view.*;
import com.yunqi.common.viewService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/view")
public class ViewController {
    private ProjectViewService ProjectViewService;
    private WorkViewService WorkViewService;
    private SchemeViewService SchemeViewService;
    private WriterViewService WriterViewService;
    private DetailViewService DetailViewService;

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

    @Autowired
    public void setMapper(DetailViewService DetailViewService) {
        this.DetailViewService = DetailViewService;
    }

    @CrossOrigin
    @RequestMapping("/project")
    public List<ProjectView> select(ProjectView ProjectView) {
        return ProjectViewService.select(ProjectView);
    }

    @CrossOrigin
    @RequestMapping("/projectCom")
    public List<ProjectView> selectCom(ProjectView ProjectView) {
        return ProjectViewService.selectCom(ProjectView);
    }

    @CrossOrigin
    @RequestMapping("/work")
    public List<WorkView> select(WorkView WorkView) throws ParseException {
        return WorkViewService.select(WorkView);
    }

    @CrossOrigin
    @RequestMapping("/workCom")
    public List<WorkView> selectCom(WorkView WorkView) throws ParseException {
        return WorkViewService.selectCom(WorkView);
    }


    @CrossOrigin
    @RequestMapping("/workAll")
    public List<WorkView> selectAll(WorkView WorkView) throws ParseException {
        return WorkViewService.selectAll(WorkView);
    }

    @CrossOrigin
    @RequestMapping("/scheme")
    public List<SchemeView> select(SchemeView SchemeView) {
        return SchemeViewService.select(SchemeView);
    }

    @CrossOrigin
    @RequestMapping("/writer") // 可核销方案
    public List<WriterView> select(WriterView WriterView) {
        return WriterViewService.select(WriterView);
    }

    @CrossOrigin
    @RequestMapping("/detail") // 核销记录
    public List<DetailView> detail(DetailView DetailView) {
        return DetailViewService.select(DetailView);
    }

}
