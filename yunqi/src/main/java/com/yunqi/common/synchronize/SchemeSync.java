package com.yunqi.common.synchronize;

import com.yunqi.common.entity.SyncScheme;
import com.yunqi.common.service.SyncSchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sync")
public class SchemeSync {
    private SyncSchemeService SyncSchemeService;

    @Autowired
    public void setMapper(SyncSchemeService SyncSchemeService) {
        this.SyncSchemeService = SyncSchemeService;
    }

    @CrossOrigin
    @RequestMapping("/Scheme")
    public void synchronize(SyncScheme SyncScheme) {
        SyncSchemeService.insert(SyncScheme);
    }
}
