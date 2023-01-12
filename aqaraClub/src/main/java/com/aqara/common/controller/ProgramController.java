package com.aqara.common.controller;

import com.aqara.common.properties.ProgramProperties;
import com.aqara.common.utils.ProgramUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/program")
public class ProgramController {
    @Autowired
    ProgramProperties ProgramProperties;

    @RequestMapping("/getToken")
    public String getToken(@RequestBody String code) {
        String res = ProgramUtil.getToken(ProgramProperties, code);
        return res;
    }
}
