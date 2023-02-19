package com.beanFind;

import com.springCore.AppConfig;
import com.springCore.member.MemberService;
import com.springCore.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextInfoFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        // System.out.println("memberService = " + memberService);
        // System.out.println("memberService = " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        // System.out.println("memberService = " + memberService);
        // System.out.println("memberService = " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구현체 타입으로 조회") // 좋은 방법은 아님. 역할에 의존해야 하기 때문이다.
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        // System.out.println("memberService = " + memberService);
        // System.out.println("memberService = " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 실패할 경우
    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX() {
        // ac.getBean("xxxx", MemberService.class);
        // MemberService xxxx = ac.getBean("xxxx", MemberService.class);

        // xxxx 라는 이름의 빈이 있는지 조회한다.
        // 없으니까 예외가 발생한다.

        // 2번째 매개변수로 전달한 동작이 실행되었을 때
        // 1번째 매개변수로 전달한 예외가 발생하는지 파악하는 test

        // 원하는 예외가 발생한다면 test에 성공한다.
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class));

    }

}
