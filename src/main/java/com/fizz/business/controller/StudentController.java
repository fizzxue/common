package com.fizz.business.controller;

import com.fizz.business.service.StudentService;
import com.fizz.common.model.RespModel;
import com.fizz.utils.page.ReqPageModel;
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
}
