package com.singleton;

import com.springCore.AppConfig;
import com.springCore.member.MemberRepository;
import com.springCore.member.MemberServiceImpl;
import com.springCore.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // memberRepository의 getter가 구체 클래스에 있어서 반환형을 Impl로 했음
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());

        // 출력해보면... 두 memberRepository가 동일하다는 걸 알 수 있다.
        // memberService -> memberRepository = com.springCore.member.MemoryMemberRepository@6f2cfcc2
        // orderService -> memberRepository = com.springCore.member.MemoryMemberRepository@6f2cfcc2

        // 이것도 같다.
        // 여기까지 보면 3번의 new가 실행되었을 것 같은데 동일한 걸 알 수 있다.
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        System.out.println("memberRepository = " + memberRepository);

    }
}
