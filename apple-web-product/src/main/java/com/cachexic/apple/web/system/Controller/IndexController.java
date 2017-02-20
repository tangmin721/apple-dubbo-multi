package com.cachexic.apple.web.system.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: tangmin
 * @date: 2017-02-18 09:38:45
 * @version: 1.0
 */
@Controller
public class IndexController {
    @RequestMapping("index")
    public String index() {
        return "index";
    }
}
