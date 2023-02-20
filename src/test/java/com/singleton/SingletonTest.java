package com.singleton;

import com.springCore.AppConfig;
import com.springCore.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

        // 두 인스턴스의 참조값이 다른지 확인
        System.out.println("memberService = " + memberService2);
        System.out.println("memberService2 = " + memberService2);

        // memberService와 memberService2가 서로 다른지 확인
        Assertions.assertThat(memberService).isNotSameAs(memberService2);
    }

}
