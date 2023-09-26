package com.yunqi.common.controller;

import com.yunqi.common.entity.WorkSheet;
import com.yunqi.common.entity.Writer;
import com.yunqi.common.service.WorkSheetService;
import com.yunqi.common.service.WriterService;
import com.yunqi.common.view.ProductView;
import com.yunqi.common.view.SchemeView;
import com.yunqi.common.viewService.ProductViewService;
import com.yunqi.common.viewService.SchemeViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

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
    @RequestMapping("/sign") // 签到
    private void sign(Integer id, String updateName) {
        WorkSheetService.sign(id, updateName);
    }

    @CrossOrigin
    @RequestMapping("/depart") // 离场
    private void depart(Integer id, String updateName) {
        WorkSheetService.depart(id, updateName);
    }


    /**
     * workId ： 工单
     * projectId ： 项目
     * headId： 负责人
     * updateName： 更新人
     **/
    @CrossOrigin
    @RequestMapping("/complete")
    private String complete(Integer workId, Integer projectId, Integer headId, String updateName) {
        ProductView ProductView = new ProductView(); //人员，产品，贡献度view
        ProductView.setWorkId(workId);
        List<ProductView> ProductList = ProductViewService.selectByWork(ProductView); // 查询该项目下的核销记录单

        String type = null; // 安装类型
        Integer writerId = null; // 核销ID
        double sumSm = 0; // 负责人安装-调试

        SchemeView SchemeView = new SchemeView();
        SchemeView.setProjectId(projectId);
        List<SchemeView> list = SchemeViewService.selectSum(SchemeView); // 实际总数
        // 当前人员的贡献度
        for (ProductView productView : ProductList) {
            Writer Writer = new Writer();
            type = productView.getType(); // 核销类型
            writerId = productView.getId(); // 核销ID
            Integer install = productView.getCustomerInstall(); // 本次安装
            Integer debug = productView.getCustomerDebug(); // 本次调试
            double headDoor = productView.getHeadDoor(); // 负责人-上门
            double installRatio = productView.getInstallRatio(); // 安装比例
            double debugRatio = productView.getDebugRatio(); // 调试比例
            Writer.setUpdateName(updateName); // 设置修改人
            Writer.setId(writerId); // 设置修改ID
            switch (type) { //单人模式
                case "安装", "调试" -> {
                    double installContribute = productView.getInstallContribute(); // 安装贡献
                    double debugContribute = productView.getDebugContribute(); // 调试贡献
                    double installSum = install * installContribute; // 数量 * 安装贡献
                    double debugSum = debug * debugContribute; // 数量 * 调试贡献
                    Writer.setType(type); //工单类型
                    Writer.setSumWork(install + debug); // 总数 = 本次安装 + 本次调试
                    Writer.setContribution(installSum + debugSum); // 贡献度
                    WriterService.simpleWriter(Writer); //单人核销修改
                    // 计算负责人的单条记录的贡献
                    if (type.equals("安装")) {
                        sumSm = sumSm + (headDoor * installRatio * install) / 2; // 上门 * 安装比例 * 核销数 / 2
                    } else {
                        sumSm = sumSm + (headDoor * installRatio * install) / 2;
                        sumSm = sumSm + (headDoor * debugRatio * debug) / 2; // 上门 * 调试比例 * 核销数 / 2
                    }
                }
                case "交底", "验收" -> {
                    if (list.get(0).getNumber() > 100) {
                        int num = list.get(0).getNumber() / 100;
                        System.out.printf("" + num);
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
            System.out.printf(String.valueOf(sumSm));
        }
        // 开始计算负责人部分
        Writer Writer = new Writer();
        if (Objects.equals(type, "交底")) {
            Writer.setProjectId(projectId); // 项目
            Writer.setTechId(headId); // 负责人
            Writer.setWorkId(workId); // 工单
            Writer.setContribution(list.get(0).getHeadDisclose() * list.get(0).getNumber() / 2); // 总交 * 总实 / 2
            Writer.setType("负责人-交底");
        }
        if (Objects.equals(type, "安装") || Objects.equals(type, "调试")) {
            Writer.setProjectId(projectId);
            Writer.setTechId(headId);
            Writer.setWorkId(workId);
            Writer.setContribution(sumSm);
            Writer.setType("负责人-上门");
        }
        WriterService.insertHead(Writer); //负责人插入
        WorkSheetService.complete(workId, updateName); // 设置工单已完成
        return "离场成功，请查看核销记录";
    }

    @CrossOrigin
    @RequestMapping("/delete")
    private void delete(Integer id, String updateName) {
        WorkSheetService.delete(id, updateName);
    }
}
