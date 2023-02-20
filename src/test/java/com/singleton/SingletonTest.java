package com.singleton;

import com.springCore.AppConfig;
import com.springCore.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.hierarchical.SingleTestExecutor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

        // 두 인스턴스의 참조값이 다른지 확인
        System.out.println("memberService = " + memberService);
        System.out.println("memberService2 = " + memberService2);

        // memberService와 memberService2가 서로 다른지 확인
        assertThat(memberService).isNotSameAs(memberService2);
    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {

        SingletonService instance = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        // SingletonService로 부터 얻은
        // 2개의 객체가

        // 정말 같은 주소에 저장되어 있는지 확인
        assertThat(instance).isSameAs(instance2);

        // same
        // equal

    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        // AppConfig appConfig = new AppConfig();
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 두 인스턴스의 참조값이 같은지 확인
        System.out.println("memberService = " + memberService);
        System.out.println("memberService2 = " + memberService2);
        // memberService = com.springCore.member.MemberServiceImpl@4c2cc639
        // memberService2 = com.springCore.member.MemberServiceImpl@4c2cc639

        // memberService와 memberService2가 서로 같은지 확인
        assertThat(memberService).isSameAs(memberService2);

    }
}
