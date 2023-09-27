package com.yunqi.common.synchronize;

import com.yunqi.common.entity.Scheme;
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

    @Autowired
    public void setMapper(SchemeService SchemeService) {
        this.SchemeService = SchemeService;
    }

    @CrossOrigin
    @RequestMapping("/Scheme")
    public void synchronize(Scheme Scheme) {
        String itemId = Scheme.getItemId();
        List<Scheme> list = SchemeService.selectItem(itemId);
        if (list.isEmpty()) {
            SchemeService.insert(Scheme);
        } else {
            SchemeService.update(Scheme);
            SchemeService.insert(Scheme);
        }
    }
}
