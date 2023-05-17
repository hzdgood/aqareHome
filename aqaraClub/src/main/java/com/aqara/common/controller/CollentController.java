package com.aqara.common.controller;

import com.aqara.common.entity.Collent;
import com.aqara.common.service.CollentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collent")
public class CollentController {
    private CollentService CollentService;

    @Autowired
    public void setMapper(CollentService CollentService) {
        this.CollentService = CollentService;
    }

    @CrossOrigin
    @RequestMapping("/synchronize")
    public void synchronize(Collent Collent) {
        String itemId = Collent.getItemId();
        List<Collent> list = CollentService.selectId(itemId);
        if (list.size() == 0) {
            CollentService.insert(Collent);
        } else {
            CollentService.update(Collent);
            CollentService.insert(Collent);
        }
    }
}
