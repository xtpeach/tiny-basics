package com.xtpeach.tiny.basics.kettle.ui.templates.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xtpeach
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping(value = "/page")
    public String home() {
        return "home/home";
    }

}
