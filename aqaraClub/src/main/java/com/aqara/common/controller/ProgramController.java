package com.aqara.common.controller;

import com.aqara.common.properties.*;
import com.aqara.common.utils.ProgramUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/program")
public class ProgramController {
    @Autowired
    ProgramProperties ProgramProperties;

    @RequestMapping("/getToken")
    public String getToken(@RequestBody String code) {
        String token = ProgramUtil.getToken(ProgramProperties, code);
        return token;
    }
}
