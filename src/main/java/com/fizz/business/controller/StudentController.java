package com.fizz.business.controller;

import com.fizz.business.service.StudentService;
import com.fizz.common.model.RespModel;
import com.fizz.utils.page.ReqPageModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author Fizz
 * @since 2019/10/1 9:31
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("/pageList")
    @ResponseBody
    public RespModel list(ReqPageModel reqPageModel) {
        return studentService.page(reqPageModel);
    }

    @GetMapping("/shiro")
    @ResponseBody
    public String shiro() {
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken("admin", "123456"));
        Session session = subject.getSession();
        System.out.println(session);
        System.out.println(session.getStartTimestamp());
        System.out.println(session.getLastAccessTime());
        subject.logout();
        return "login";
    }

}
