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

    private WriterViewService WriterViewService;

    private WriterDetailService WriterDetailService;

    private WriterService WriterService;

    @Autowired
    public void setMapper(WorkSheetService WorkSheetService) {
        this.WorkSheetService = WorkSheetService;
    }

    @Autowired
    public void setMapper(WriterViewService WriterViewService) {
        this.WriterViewService = WriterViewService;
    }

    @Autowired
    public void setMapper(WriterDetailService WriterDetailService) {
        this.WriterDetailService = WriterDetailService;
    }

    @Autowired
    public void setMapper(WriterService WriterService) {
        this.WriterService = WriterService;
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
        Integer productId = null; //产品ID

        // 1、 插入WriterDetail表 数据
        WriterView w = new WriterView();
        w.setWorkId(id);
        List<WriterView> list = WriterViewService.select(w); // 查询
        WriterDetail WriterDetail = new WriterDetail();
        if(!list.isEmpty()) {
            WriterView writerView = list.get(0);
            productId = writerView.getProductId();
            WriterDetail.setWorkId(writerView.getWorkId());
            WriterDetail.setProjectId(writerView.getProjectId());
            WriterDetail.setProductId(writerView.getProductId());
            WriterDetail.setType(writerView.getType());
            WriterDetail.setWriteTime(writerView.getWriteTime());
        } else {
            return "未查到到核销内容！";
        }

        //计算 number
        Writer Writer = new Writer();
        Writer.setWorkId(id);
        List<Writer> list1 =  WriterService.selectNumber(Writer);
        if(!list.isEmpty()) {
            Writer writer = list1.get(0);
            WriterDetail.setNumber(writer.getInstall() + writer.getDebug());
        } else {
            return "核销数量为空！";
        }
        // 2、计算贡献度

        WriterDetailService.insert(WriterDetail);
        WorkSheetService.depart(id, updateName);
        return "离场成功，请查看核销记录";
    }

    @CrossOrigin
    @RequestMapping("/delete")
    private void delete(Integer id, String updateName) {
        WorkSheetService.delete(id, updateName);
    }
}
