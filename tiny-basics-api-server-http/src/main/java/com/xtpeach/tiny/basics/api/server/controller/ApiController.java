package com.xtpeach.tiny.basics.api.server.controller;

import com.xtpeach.tiny.basics.common.response.Response;
import com.xtpeach.tiny.id.utils.TinyId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @PostMapping(value = "query")
    public Response<String> query() {
        return Response.success();
    }

    @GetMapping(value = "id/next")
    public Response<Long> next() {
        // 调用 tiny-id 获取 id
        Long nextId = TinyId.nextId("test");
        return Response.success(nextId);
    }

}
