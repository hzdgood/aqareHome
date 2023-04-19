package com.aqara.common.entity;

import java.util.Date;

public class Project {
    private Integer id;
    private String itemId;
    private String code; // 项目编码
    private String task; // 目前任务
    private String progress; // 装修进度
    private String saleConfirm; // 销售确认
    private String end;// 完结
    private String customer; // 客户
    private String projectId; // 项目ID
    private String name;// 项目姓名
    private String telephone;// 项目电话
    private String department; // 门店
    private String sales; //销售
    private String techId; //技术
    private String projectType;// 项目类型
    private String designer; // 设计师
    private String intention; // 意向
    private String track; // 跟踪
    private String preferential; // 优惠
    private String totalPrice; // 总价
    private String fullPrice; // 全款价格
    private String totalAmount; // 收款总额
    private String mainProject;// 主项目
    private String groupID;// 群ID
    private String address; // 地址
    private String createName;
    private Date createTime;
    private String updateName;
    private Date updateTime;



    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
