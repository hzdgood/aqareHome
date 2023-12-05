package com.aqara.common.controller;

import com.aqara.common.entity.Suite;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/suite")
public class SuiteController {
    @RequestMapping("/getToken")
    public String getToken(Suite Suite) {
        return "";
    }
}
