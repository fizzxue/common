package com.fizz.rest;

import com.fizz.common.model.RespModel;
import com.fizz.config.shiro.ShiroConfig;
import com.fizz.utils.date.RespUtils;
import com.fizz.utils.hash.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Fizz
 * @since 2019/9/27 15:10
 */
@Controller
public class DemoController {

    /**
     * 测试慢执行
     */
    @RequestMapping("/hello/cycle")
    @ResponseBody
    public String helloCycle(@RequestParam Integer cycle) throws InterruptedException {
        a();
        return "hello cycle over";
    }

    @RequestMapping("/cycle")
    @ResponseBody
    public void cycle(@RequestParam Integer cycle) {
        while (true) {
            System.out.println("=====================");
            byte[] b = new byte[1024*1024*5];
        }
//        return "cycle";
    }

    public void a() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        b();
    }

    public void b() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        c();
    }

    public void c() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
    }

    @GetMapping("/table/list")
    @ResponseBody
    public RespModel<Map<String,String>> list() throws Exception {
        Map<String,String> map = new HashMap<>(2);
        map.put("total", "20");
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken("zgl", "123456"));
//        List<Student> list = studentService.list();
//        map.put("items", list);
        return RespUtils.success(map);
    }


    public static void main(String[] args) {
        System.out.println(MD5Utils.md5("123456", "zgl" + ShiroConfig.SALT));
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
