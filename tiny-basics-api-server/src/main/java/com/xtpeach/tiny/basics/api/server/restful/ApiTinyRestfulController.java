package com.xtpeach.tiny.basics.api.server.restful;

import com.xtpeach.tiny.basics.common.response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ApiTinyRestfulController {

    @PostMapping(value = "query")
    public Response<String> query() {
        return Response.success();
    }

}
