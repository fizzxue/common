package com.fizz.rest;

import com.fizz.business.model.Student;
import com.fizz.business.service.StudentService;
import com.fizz.common.model.RespModel;
import com.fizz.utils.date.RespUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Fizz
 * @since 2019/9/27 15:10
 */
@Controller
public class DemoController {

    @Resource
    private StudentService studentService;

    @GetMapping("/table/list")
    @ResponseBody
    public RespModel list() throws Exception {
        Map map = new HashMap(2);
        map.put("total", "20");
        List<Student> list = studentService.list();
        map.put("items", list);
        return RespUtils.success(map);
    }

    @PostMapping("/user/login")
    @ResponseBody
    public String userLogin() {
        //{"code":20000,"data":{"token":"admin-token"}}
        return "{\"code\":20000,\"data\":{\"token\":\"admin-token\"}}";
    }

    @GetMapping("/user/info")
    @ResponseBody
    public String userInfo() {
        //{"code":20000,"data":{"roles":["admin"],"introduction":"I am a super administrator","avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif","name":"Super Admin"}}
        return "{\"code\":20000,\"data\":{\"roles\":[\"admin\"],\"introduction\":\"I am a super administrator\",\"avatar\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif\",\"name\":\"Super Admin\"}}";
    }
}
