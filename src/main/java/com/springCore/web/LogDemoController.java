package com.springCore.web;

import javax.servlet.http.HttpServletRequest;

import com.springCore.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody // 반환되는 문자를 그대로 화면에 출력하도록 함
    public String logDemo(HttpServletRequest request) {

        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLogger.getClass());
        // 출력 내용
        // myLogger = class com.springCore.common.MyLogger$$EnhancerBySpringCGLIB$$e8e206c4

        // 가짜 mylogger라는 걸 알 수 있음. CGLIB를 이용해서 가짜 프록시 객체를 만들어서 주입함

        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }
}
