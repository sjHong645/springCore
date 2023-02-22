package com.springCore.common;

import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]"
                         + "[" + requestURL + "] "
                         + message );
    }

    // 스프링 bean 생성 & 의존관계 주입 이후의 콜백 = 초기화 콜백
    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString(); // 다른 HTTP 요청과 구분해줌
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    // 스프링이 종료되기 전의 콜백
    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
