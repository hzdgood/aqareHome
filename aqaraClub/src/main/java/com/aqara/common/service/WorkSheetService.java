package com.aqara.common.service;

import com.aqara.common.entity.WorkSheet;
import com.aqara.common.mapper.WorkSheetMapper;
import com.aqara.common.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WorkSheetService {
    private WorkSheetMapper WorkSheetMapper;

    @Autowired
    public void setMapper(WorkSheetMapper WorkSheetMapper) {
        this.WorkSheetMapper = WorkSheetMapper;
    }

    public List<WorkSheet> select() {
        return WorkSheetMapper.select();
    }

    public List<WorkSheet> selectId(String itemId) {
        return WorkSheetMapper.selectId(itemId);
    }

    public void insert(WorkSheet WorkSheet) {
        WorkSheetMapper.insert(WorkSheet);
    }

    public void update(WorkSheet WorkSheet) {
        WorkSheetMapper.update(WorkSheet);
    }

    public void delete(Integer id) {
        WorkSheetMapper.delete(id);
    }

    public String getWorkSend() {
        List<WorkSheet> list = WorkSheetMapper.select();
        List<String> users = new ArrayList<>();
        List<String> names = new ArrayList<>();
        StringBuilder str = new StringBuilder("**今日完成工单情况** \n");
        for (int i = 0; i < list.size(); i++) {
            WorkSheet WorkSheet = list.get(i);
            String engName = WorkSheet.getEngName();
            String userName = WorkSheet.getUserName();
            String count = WorkSheet.getCount();
            String restDay = WorkSheet.getRestDay();
            if (count == null || count.equals("")) {
                users.add(userName + "  " + restDay + "休  ");
            } else {
                if (!getStatus(names, engName)) {
                    List<WorkSheet> techList = getTechWork(list, engName);
                    str.append(userName).append(" ").append(restDay).append("休  ").append("完成工单数：").append(techList.size()).append("\n");
                    for (WorkSheet w : techList) {
                        String res = w.getCustom() + "-" + w.getWorkType() + "-" + w.getActualWork() + "-" + w.getWorkStatus();
                        str.append("[").append(res).append("](https://app.huoban.com/tables/2100000015054992/items/").append(w.getItemId()).append(")").append("    ");
                    }
                    str.append("\n");
                    names.add(engName);
                }
            }
        }
        for (String user : users) {
            str.append(user).append("工单数:").append(0).append("\n");
        }
        return str.toString();
    }

    public String getWorkSendTow() {
        List<WorkSheet> list = WorkSheetMapper.selectTom();
        List<String> users = new ArrayList<>();
        List<String> names = new ArrayList<>();
        StringBuilder str = new StringBuilder("**明日工单情况** \n");
        for (int i = 0; i < list.size(); i++) {
            WorkSheet WorkSheet = list.get(i);
            String engName = WorkSheet.getEngName();
            String userName = WorkSheet.getUserName();
            String count = WorkSheet.getCount();
            String restDay = WorkSheet.getRestDay();
            if (count == null || count.equals("")) {
                users.add(userName + "  " + restDay + "休  ");
            } else {
                if (!getStatus(names, engName)) {
                    List<WorkSheet> techList = getTechWork(list, engName);
                    str.append(userName).append(" ").append(restDay).append("休  ").append("工单数：").append(techList.size()).append("\n");
                    for (WorkSheet w : techList) {
                        String res = w.getCustom() + "-" + w.getWorkType() + "-" + w.getArea();
                        str.append("[").append(res).append("](https://app.huoban.com/tables/2100000015054992/items/").append(w.getItemId()).append(")").append("    ");
                    }
                    str.append("\n");
                    names.add(engName);
                }
            }
        }
        for (String user : users) {
            str.append(user).append("工单数:").append(0).append("\n");
        }
        return str.toString();
    }

    public String getNoVisit() {
        StringBuilder str = new StringBuilder("**未预约上门工单** \n");
        List<WorkSheet> list = WorkSheetMapper.selectNotVisit();
        for (WorkSheet WorkSheet : list) {
            String custom = WorkSheet.getCustom();
            String technology = WorkSheet.getTechnology();
            Date time = WorkSheet.getCreateTime();
            Long hours = TimeUtil.getDaysDifference(time);
            str.append("客户: ").append(custom).append("   技术: ").append(technology).append("   ");
            str.append("[立即预约](https://app.huoban.com/tables/2100000015054992/items/")
                    .append(WorkSheet.getItemId())
                    .append(")  ").append("已过").append(hours).append("小时").append("\n");
        }
        return str.toString();
    }

    public String getNotComplete() {
        StringBuilder str = new StringBuilder("**今日未完成工单** \n");
        List<WorkSheet> list = WorkSheetMapper.getNotComplete();
        for (WorkSheet WorkSheet : list) {
            String custom = WorkSheet.getCustom();
            String userName = WorkSheet.getUserName();
            Date signTime = WorkSheet.getSignInTime();
            if (signTime == null){
                str.append("客户: ").append(custom).append("   技术: ").append(userName).append("  没有签到 ");
                str.append("[点击查看](https://app.huoban.com/tables/2100000015054992/items/")
                        .append(WorkSheet.getItemId())
                        .append(")  ").append("\n");
            } else {
                str.append("客户: ").append(custom).append("   技术: ").append(userName).append("  ");
                str.append("[点击查看](https://app.huoban.com/tables/2100000015054992/items/")
                        .append(WorkSheet.getItemId())
                        .append(")  ").append("\n");
            }
        }
        return str.toString();
    }

    private static List<WorkSheet> getTechWork(List<WorkSheet> list, String engName) {
        List<WorkSheet> newList = new ArrayList<>();
        for (WorkSheet WorkSheet : list) {
            if (engName.equals(WorkSheet.getEngName())) {
                newList.add(WorkSheet);
            }
        }
        return newList;
    }

    private boolean getStatus(List<String> names, String engName) {
        boolean b = false;
        for (String name : names) {
            if (engName.equals(name)) {
                b = true;
                break;
            }
        }
        return b;
    }
}
