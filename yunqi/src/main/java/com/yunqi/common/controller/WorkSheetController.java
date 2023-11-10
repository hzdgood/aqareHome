package com.yunqi.common.controller;

import com.yunqi.common.entity.*;
import com.yunqi.common.service.*;
import com.yunqi.common.view.*;
import com.yunqi.common.viewService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/workSheet")
public class WorkSheetController {
    private WorkSheetService WorkSheetService;

    private ProductViewService ProductViewService;

    private WriterService WriterService;

    private SchemeViewService SchemeViewService;

    private WorkTimeService WorkTimeService;

    private StTimeViewService StTimeViewService;

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

    @Autowired
    public void setMapper(WorkTimeService WorkTimeService) {
        this.WorkTimeService = WorkTimeService;
    }

    @Autowired
    public void setMapper(StTimeViewService StTimeViewService) {
        this.StTimeViewService = StTimeViewService;
    }

    @CrossOrigin
    @RequestMapping("/insert")
    private String insert(WorkSheet WorkSheet) {
        // 新增工单
        Integer projectId = WorkSheet.getProjectId();
        List<StTimeView> list = StTimeViewService.selectId(projectId);
        if(!list.isEmpty()) {
            if(Objects.equals(WorkSheet.getType(), "安装")) {
                WorkSheet.setStandardTime(list.get(0).getStInstall());
            } else if(Objects.equals(WorkSheet.getType(), "调试")) {
                double st = list.get(0).getStInstall() + list.get(0).getStDebug();
                WorkSheet.setStandardTime(st);
            } else {
                WorkSheet.setStandardTime(2);
            }
        } else {
            System.out.println("插入问题项目ID:" + projectId);
            WorkSheet.setStandardTime(2);
        }
        // 分别签到离开 插入个人工单表
        WorkSheetService.insert(WorkSheet);
        insertSimpleWork(WorkSheet);
        return "发单完成";
    }

    private void insertSimpleWork(WorkSheet WorkSheet) {
        WorkTime WorkTime = new WorkTime();
        String techIds = WorkSheet.getTechIds();
        String[] techs = techIds.split(",");
        if (techs.length > 0) {
            for (String tech : techs) {
                WorkTime.setProjectId(WorkSheet.getProjectId());
                WorkTime.setStatus("待上门");
                WorkTime.setTechId(tech);
                WorkTime.setCreateName(WorkSheet.getCreateName());
                WorkTime.setWorkId(WorkSheet.getId());
                WorkTimeService.insert(WorkTime);
            }
        } else {
            WorkTime.setProjectId(WorkSheet.getProjectId());
            WorkTime.setStatus("待上门");
            WorkTime.setTechId(WorkSheet.getTechIds());
            WorkTime.setCreateName(WorkSheet.getCreateName());
            WorkTime.setWorkId(WorkSheet.getId());
            WorkTimeService.insert(WorkTime);
        }
    }

    @CrossOrigin
    @RequestMapping("/update") // 今日总结，下次上门
    private String update(WorkSheet WorkSheet) {
        WorkSheetService.update(WorkSheet);
        return "核销完成";
    }

    @CrossOrigin
    @RequestMapping("/updateInfo") //工单修改
    private String updateInfo(WorkSheet WorkSheet) {
        WorkSheetService.updateInfo(WorkSheet);
        return "修改完成";
    }

    @CrossOrigin
    @RequestMapping("/updatePerson") //工单修改 上门技术
    private String updatePerson(WorkSheet WorkSheet) {
        WorkSheetService.updatePerson(WorkSheet);
        return "修改完成";
    }

    @CrossOrigin
    @RequestMapping("/updateHead") //工单修改 负责人
    private String updateHead(WorkSheet WorkSheet) {
        WorkSheetService.updateHead(WorkSheet);
        return "修改完成";
    }

    /**
     * workId ： 工单
     * projectId ： 项目
     * headId： 负责人
     * updateName： 更新人
     * type : 类型
     **/
    @CrossOrigin
    @RequestMapping("/computer")
    private String computer(
            Integer timeId, Integer workId, Integer projectId,
            Integer headId, String updateName, String type
    ) {
        ProductView ProductView = new ProductView(); //人员，产品，贡献度view
        ProductView.setWorkId(workId);
        ProductView.setTechIds(updateName); // 分开完成
        List<ProductView> ProductList = ProductViewService.selectByWork(ProductView); // 查询该项目下的核销记录单
        Integer writerId = null; // 核销ID
        double sumSm = 0; // 负责人安装-调试
        int sumInstall = 0; // 总安装
        int sumDebug = 0; // 总调试

        SchemeView SchemeView = new SchemeView();
        SchemeView.setProjectId(projectId);
        List<SchemeView> list = SchemeViewService.selectSum(SchemeView); // 实际总数
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        // 当前人员的贡献度  经过核销的
        for (ProductView productView : ProductList) {
            writerId = productView.getId(); // 核销ID
            Integer install = productView.getCustomerInstall(); // 本次安装
            Integer debug = productView.getCustomerDebug(); // 本次调试
            double headDoor = productView.getHeadDoor(); // 负责人-上门
            double installRatio = productView.getInstallRatio(); // 安装比例
            double debugRatio = productView.getDebugRatio(); // 调试比例

            Writer Writer = new Writer();
            Writer.setUpdateName(updateName); // 设置修改人
            Writer.setId(writerId); // 设置修改ID
            switch (type) { //单人模式
                case "安装", "调试" -> {
                    double installContribute = productView.getInstallContribute(); // 安装贡献
                    double debugContribute = productView.getDebugContribute(); // 调试贡献
                    double installSum = install * installContribute; // 数量 * 安装贡献
                    double debugSum = debug * debugContribute; // 数量 * 调试贡献
                    sumInstall = sumInstall + install;
                    sumDebug = sumDebug + debug;
                    Writer.setType(type); //工单类型
                    if (type.equals("安装")) {
                        Writer.setSumWork(install);  // 总数
                        Writer.setContribution(installSum);  // 贡献度
                    } else {
                        Writer.setSumWork(install + debug);
                        Writer.setContribution( installSum + debugSum );  // 贡献度
                    }
                    WriterService.simpleWriter(Writer); //单人核销修改
                    // 计算负责人的单条记录的贡献
                    if (type.equals("安装")) {
                        sumSm = sumSm + (headDoor * installRatio * install) / 2; // 上门 * 安装比例 * 核销数 / 2
                    } else {
                        sumSm = sumSm + (headDoor * installRatio * install) / 2;
                        sumSm = sumSm + (headDoor * debugRatio * debug) / 2; // 上门 * 调试比例 * 核销数 / 2
                    }
                }
            }
        }
        // 无需核销的
        switch (type) {
            case "交底", "验收" -> {
                Writer Writer = new Writer();
                int num = list.get(0).getNumber() / 100;
                if (list.get(0).getNumber() > 100) {
                    Writer.setContribution(300.0);
                } else {
                    Writer.setContribution(150.0);
                }
                Writer.setProjectId(projectId);
                Writer.setWorkId(workId);
                Writer.setTechId(headId);
                Writer.setType(type);
                Writer.setCreateName(updateName);
                WriterService.insert(Writer); // 新增
            }
            case "售后" -> {
                Writer Writer = new Writer();
                Writer.setProjectId(projectId);
                Writer.setWorkId(workId);
                Writer.setTechId(headId);
                Writer.setType(type);
                Writer.setContribution(150.0);
                Writer.setCreateName(updateName);
                WriterService.insert(Writer); // 新增
            }
            case "检测" -> { // 验收只能上门类型
                for (ProductView productView : ProductList) {
                    Writer Writer = new Writer();
                    writerId = productView.getId();
                    Writer.setContribution(productView.getCost() / 2);
                    Writer.setUpdateName(updateName); // 设置修改人
                    Writer.setId(writerId); // 设置修改ID
                    WriterService.simpleWriter(Writer);
                }
            }
        }
        // 开始计算负责人部分 BUG 会出现多个。。
        Writer Writer = new Writer();
        if (Objects.equals(type, "交底")) {
            // 有个小坑 交底单只能一次
            Writer.setProjectId(projectId); // 项目
            Writer.setTechId(headId); // 负责人
            Writer.setWorkId(workId); // 工单
            Writer.setContribution(list.get(0).getContribution()); // 总交 * 总实 / 2
            Writer.setCreateName(updateName);
            Writer.setType("负责人-交底");
            WriterService.insertHead(Writer); //负责人插入
        }
        if (Objects.equals(type, "安装") || Objects.equals(type, "调试")) {
            Writer.setProjectId(projectId);
            Writer.setTechId(headId);
            Writer.setWorkId(workId);
            Writer.setCreateName(updateName);
            Writer.setSumWork(sumInstall + sumDebug); // 总安装 + 总调试
            Writer.setContribution(sumSm);
            Writer.setType("负责人-上门");
            WriterService.insertHead(Writer); //负责人插入
        }
        WorkTimeService.complete(timeId, updateName); // 设置工单已完成
        return "核销计算完成";
    }

    @CrossOrigin
    @RequestMapping("/complete")
    private String complete(Integer workId) {
        WorkSheetService.complete(workId);
        return "工单完成";
    }

    @CrossOrigin
    @RequestMapping("/delete")
    private void delete(Integer id, String updateName) {
        WorkSheetService.delete(id, updateName);
    }
}
