package com.yunqi.common.synchronize;

import com.yunqi.common.entity.Project;
import com.yunqi.common.entity.Sales;
import com.yunqi.common.entity.Tech;
import com.yunqi.common.service.ProjectService;
import com.yunqi.common.service.SalesService;
import com.yunqi.common.service.TechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sync")
public class ProjectSync {
    @Autowired
    private ProjectService ProjectService;

    @Autowired
    private SalesService SalesService;

    @Autowired
    private TechService TechService;

    @CrossOrigin
    @RequestMapping("/project")
    public void synchronize(Project Project) {
        String itemId = Project.getItemId();
        List<Tech> tech = TechService.select();
        List<Sales> sales = SalesService.select();
        List<Project> list = ProjectService.selectItem(itemId);
        for (Sales sale : sales) {
            if (sale.getName().equals(Project.getSale())) {
                Project.setSaleId(sale.getId());
            }
        }
        for (Tech techs : tech) {
            if (techs.getName().equals(Project.getTech())) {
                Project.setTechId(techs.getId());
            }
        }
        if (list.isEmpty()) {
            ProjectService.insert(Project);
        } else {
            ProjectService.update(Project);
        }
    }
}
