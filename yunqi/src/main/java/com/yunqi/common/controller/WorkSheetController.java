package com.yunqi.common.controller;

import com.yunqi.common.entity.*;
import com.yunqi.common.service.*;
import com.yunqi.common.view.*;
import com.yunqi.common.viewService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/workSheet")
public class WorkSheetController {
    private WorkSheetService WorkSheetService;

    private ProductViewService ProductViewService;

    private WriterService WriterService;

    private SchemeViewService SchemeViewService;

    @Autowired
    public void setMapper(WorkSheetService WorkSheetService) {
        this.WorkSheetService = WorkSheetService;
    }

    @Autowired
    public void setMapper(ProductViewService ProductViewService) {
        this.ProductViewService = ProductViewService;
    }

    @Autowired
    public void setMapper(WriterService WriterService) {
        this.WriterService = WriterService;
    }

    @Autowired
    public void setMapper(SchemeViewService SchemeViewService) {
        this.SchemeViewService = SchemeViewService;
    }

    @CrossOrigin
    @RequestMapping("/insert")
    private void insert(WorkSheet WorkSheet) {
        WorkSheetService.insert(WorkSheet);
    }

    @CrossOrigin
    @RequestMapping("/update") //核销完成
    private String update(WorkSheet WorkSheet) {
        WorkSheetService.update(WorkSheet);
        return "核销完成";
    }

    @CrossOrigin
    @RequestMapping("/updateInfo") //核销完成
    private String updateInfo(WorkSheet WorkSheet) {
        WorkSheetService.updateInfo(WorkSheet);
        return "修改完成";
    }

    @CrossOrigin
    @RequestMapping("/sign")
    private void sign(Integer id, String updateName) { WorkSheetService.sign(id, updateName); }

    @CrossOrigin
    @RequestMapping("/depart") // 离场
    private String depart(Integer id, String updateName) {
        ProductView ProductView = new ProductView(); //核销贡献度view
        ProductView.setWorkId(id);
        List<ProductView> ProductList = ProductViewService.selectByWork(ProductView);
        // 当前人员的贡献度
        for(ProductView productView: ProductList) {
            Writer Writer = new Writer();
            String type = productView.getType();
            Integer install = productView.getCustomerInstall();
            Integer debug = productView.getCustomerDebug();
            switch (type) { //单人模式
                case "安装", "调试" -> {
                    double installContribute = productView.getInstallContribute();
                    double debugContribute = productView.getDebugContribute();
                    double installSum = install * installContribute; // 数量 * 安装贡献
                    double debugSum = debug * debugContribute; // 数量 * 调试贡献
                    Writer.setType(type);
                    Writer.setSumWork(install + debug);
                    Writer.setContribution(installSum + debugSum);
                    WriterService.simpleWriter(Writer); //单人核销修改
                }
                case "交底", "验收" -> {
                    SchemeView SchemeView = new SchemeView();
                    SchemeView.setProjectId(productView.getProjectId());
                    List<SchemeView> list = SchemeViewService.selectSum(SchemeView); // 实际总数
                    if(list.get(0).getNumber() > 100){
                        int num = list.get(0).getNumber() / 100;
                        System.out.printf(""+num);
                        Writer.setContribution(num * 150.0);
                    } else {
                        Writer.setContribution(150.0);
                    }
                    Writer.setType(type);
                    WriterService.simpleWriter(Writer);
                }
                case "售后" -> {
                    Writer.setType(type);
                    Writer.setContribution(150.0);
                    WriterService.simpleWriter(Writer); //单人核销修改
                }
            }
        }
        WorkSheetService.depart(id, updateName);
        return "离场成功，请查看核销记录";
    }

    @CrossOrigin
    @RequestMapping("/delete")
    private void delete(Integer id, String updateName) {
        WorkSheetService.delete(id, updateName);
    }
}
