package com.xtpeach.tiny.basics.kettle.ui.templates.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xtpeach
 */
@Controller
@RequestMapping("/index")
public class IndexPageController {

    /**
     * 主页
     *
     * @return
     */
    @GetMapping(value = "/page")
    public String index() {
        return "index/index";
    }

    /**
     * 控制台
     *
     * @return
     */
    @GetMapping(value = "/console")
    public String console() {
        return "index/console";
    }

}
