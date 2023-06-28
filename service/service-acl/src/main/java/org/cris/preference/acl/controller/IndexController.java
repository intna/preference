package org.cris.preference.acl.controller;

import org.springframework.web.bind.annotation.*;
import org.cris.preference.result.Result;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")
public class IndexController {

    @PostMapping("login")
    public Result login() {
        Map<String,Object> map = new HashMap<>();
        map.put("token","admin-token");
        return Result.ok(map);
    }

    @GetMapping("info")
    public Result info(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","cirs");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }

    @PostMapping("logout")
    public Result logout(){
        return Result.ok("ok");
    }
}
