package com.aqara.common.controller;

import com.aqara.common.entity.Coordinate;
import com.aqara.common.entity.Huoban;
import com.aqara.common.entity.PageReq;
import com.aqara.common.entity.User;
import com.aqara.common.service.HuobanService;
import com.aqara.common.service.UserService;
import com.aqara.common.utils.CoordinateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    HuobanService huobanService;

    @CrossOrigin
    @RequestMapping("/select")
    public List<User> select(String username, String engName) {
        return userService.select(username, engName);
    }

    @CrossOrigin
    @RequestMapping("/selectAll")
    public Map<String, Object> selectAll(@RequestBody PageReq PageReq) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<User> list = userService.selectAll(PageReq);
        if (PageReq.getPageNo() != null || PageReq.getPageSize() != null) {
            Integer pageSize = Integer.parseInt(PageReq.getPageSize());
            Integer pageNo = Integer.parseInt(PageReq.getPageNo());
            map.put("pageNo", pageNo);
            map.put("pageSize", pageSize);
            map.put("totalCount", list.size());
            map.put("totalPage", list.size() / pageSize);
        }
        map.put("data", list);
        return map;
    }

    @CrossOrigin
    @RequestMapping("/insert")
    public void insert(User user) {
        userService.insert(user);
    }

    @CrossOrigin
    @RequestMapping("/update")
    public void update(User user) {
        userService.update(user);
    }

    @CrossOrigin
    @RequestMapping("/delete")
    public void delete(String ids) {
        List<Integer> list = new ArrayList<Integer>();
        if (ids.contains(",")) {
            String[] a = ids.split(",");
            for (int i = 0; i < a.length; i++) {
                list.add(Integer.parseInt(a[i]));
            }
        } else {
            list.add(Integer.parseInt(ids));
        }
        userService.deleteId(list);
    }

    @CrossOrigin
    @RequestMapping("/synchronize")
    public void synchronize() {
        List<Huoban> list = huobanService.select();
        Huoban Huoban = list.get(list.size() - 1);
        String ticket = Huoban.getTicket();
        userService.delete();
        userService.synchronize(ticket);
    }

    /**
     * 根据地址获取坐标
     */
    @CrossOrigin
    @RequestMapping("/getCoordinate")
    public Coordinate getCoordinate(@RequestBody String address) {
        return CoordinateUtil.getCoordinate(address);
    }
}
