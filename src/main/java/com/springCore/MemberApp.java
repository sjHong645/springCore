package com.springCore;

import com.springCore.member.Grade;
import com.springCore.member.Member;
import com.springCore.member.MemberService;
import com.springCore.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();

        // AppConfig에 있는 환경설정 정보를 가지고
        // Spring이 각각의 정보들을 Bean 컨테이너에 저장해서 관리

        /*
        Creating shared instance of singleton bean 'appConfig'
        Creating shared instance of singleton bean 'memberRepository'
        Creating shared instance of singleton bean 'memberService'
        Creating shared instance of singleton bean 'discountPolicy'
        Creating shared instance of singleton bean 'orderService'

        main 메소드를 실행하면 나타나는 로그 기록
        ==> AppConfig 에서 설정한 메소드들을 빈으로 만들어서 저장한 걸 알 수 있다.
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // 컨테이너에 있는 것들 중에서
        // 이름이 memberService인 객체를 찾는다. 반환 타입은 MemberService 타입
        // 기본적으로 이름은 메소드의 이름으로 저장된다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());

    }
}
