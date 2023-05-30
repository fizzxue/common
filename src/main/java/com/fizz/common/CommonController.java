package com.fizz.common;


import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CommonController {

    @GetMapping(path = "http/basic/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String authorization = request.getHeader("Authorization");
        System.out.println("Authorization: " + authorization);

        if (authorization == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setHeader("WWW-Authenticate", "Basic realm=\"Realm\"");
        } else {
            String credentials = authorization.substring("Basic ".length());
            byte[] decodedCredentials = Base64Utils.decode(credentials.getBytes("UTF-8"));
            System.out.println("Decoded Credentials: " + new String(decodedCredentials));

            response.setStatus(HttpStatus.OK.value());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");

            Map<String, Object> result = new HashMap<>();
            result.put("message", HttpStatus.OK.name());
            result.put("ip", request.getRemoteAddr());
            result.put("credentials", new String(decodedCredentials));

            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(result));
            writer.flush();
            writer.close();
        }
    }
}
