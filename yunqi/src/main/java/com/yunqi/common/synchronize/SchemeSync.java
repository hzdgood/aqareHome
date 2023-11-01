package com.yunqi.common.synchronize;

import com.yunqi.common.entity.Product;
import com.yunqi.common.entity.Project;
import com.yunqi.common.entity.Scheme;
import com.yunqi.common.service.ProductService;
import com.yunqi.common.service.ProjectService;
import com.yunqi.common.service.SchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sync")
public class SchemeSync {
    private SchemeService SchemeService;

    private ProjectService ProjectService;
    private ProductService ProductService;

    @Autowired
    public void setMapper(SchemeService SchemeService) {
        this.SchemeService = SchemeService;
    }
    @Autowired
    public void setMapper(ProjectService ProjectService) {
        this.ProjectService = ProjectService;
    }

    @Autowired
    public void setMapper(ProductService ProductService) {
        this.ProductService = ProductService;
    }

    @CrossOrigin
    @RequestMapping("/Scheme")
    public void synchronize(Scheme Scheme) {
        String itemId = Scheme.getItemId();
        String projectId = Scheme.getProjectId() + "";
        String productId = Scheme.getProductId() + "";
        List<Scheme> list = SchemeService.selectItem(itemId);
        List<Project> Project = ProjectService.select();
        List<Product> Product = ProductService.select();
        if (list.isEmpty()) {
            for (Project p : Project) {
                if (p.getItemId().equals(projectId)) {
                    Scheme.setProjectId(p.getId());
                }
            }
            for (Product p : Product) {
                if (p.getItemId().equals(productId)) {
                    Scheme.setProductId(p.getId());
                }
            }
            SchemeService.insert(Scheme);
        } else {
            SchemeService.update(Scheme);
        }
    }
}
