package com.yunqi.common.synchronize;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sync")
public class SchemeSync {
//    @Autowired
//    private SchemeService SchemeService;
//
//    @Autowired
//    private ProjectService ProjectService;
//
//    @Autowired
//    private ProductService ProductService;

//    @CrossOrigin
//    @RequestMapping("/Scheme")
//    public void synchronize(SyncScheme SyncScheme) {
//        String itemId = SyncScheme.getItemId();
//        List<Scheme> scheme = SchemeService.selectItem(itemId);
//        if (scheme.isEmpty()) { // 如果找不到itemId 新增
//            List<Project> Project = ProjectService.selectItem(SyncScheme.getProjectId());
//            List<Product> Product = ProductService.selectItem(SyncScheme.getProductId());
//            Scheme Scheme = new Scheme();
//            if (!Project.isEmpty()) { // 查询产品 查询项目
//                Scheme.setProjectId(Project.get(0).getId());
//                if (!Product.isEmpty()) { // 查询产品
//                    Scheme.setProductId(Product.get(0).getId());
//                    Scheme.setItemId(itemId);
//                    Scheme.setNumber(Integer.parseInt(SyncScheme.getNumber()));
//                    Scheme.setNotSent(Integer.parseInt(SyncScheme.getNotSent()));
//                    Scheme.setNotInstalled(Double.parseDouble(SyncScheme.getNotInstalled()));
//                    Scheme.setUnregulated(Double.parseDouble(SyncScheme.getUnregulated()));
//                    Scheme.setCreateName("导入数据");
//                    SchemeService.insert(Scheme); // 插入方案表
//                } else {
//                    System.out.println("未发现的产品ID:" + SyncScheme.getProductId());
//                }
//            } else {
//                System.out.println("未发现的项目ID:" + SyncScheme.getProjectId() + "-" + SyncScheme.getName());
//            }
//        } else { // 如果有itemId 修改
//            Scheme Scheme = scheme.get(0);
//            Scheme.setNumber(Integer.parseInt(SyncScheme.getNumber()));
//            Scheme.setNotSent(Integer.parseInt(SyncScheme.getNotSent()));
//            double s = Double.parseDouble(SyncScheme.getNotInstalled()) - Scheme.getInstallNumber();
//            double t = Double.parseDouble(SyncScheme.getUnregulated()) - Scheme.getDebugNumber();
//            Scheme.setNotInstalled(s);
//            Scheme.setUnregulated(t);
//            Scheme.setItemId(itemId);
//            SchemeService.update(Scheme); // 修改方案表
//        }
//    }
}
