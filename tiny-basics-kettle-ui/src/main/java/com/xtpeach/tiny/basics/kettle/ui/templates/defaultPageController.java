package com.xtpeach.tiny.basics.kettle.ui.templates;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 默认展示
 */
@Controller
public class defaultPageController {

    /**
     * 默认展示页面
     *
     * @param response
     */
    @GetMapping(value = "/")
    public void index(HttpServletResponse response) {
        response.setStatus(302);
        response.setHeader("Location", "./views/index.html");
    }

}
