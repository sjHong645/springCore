package com.springCore;

import com.springCore.member.Grade;
import com.springCore.member.Member;
import com.springCore.member.MemberService;
import com.springCore.member.MemberServiceImpl;
import com.springCore.order.Order;
import com.springCore.order.OrderService;
import com.springCore.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

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
        // orderService도 마찬가지.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        // MemberService memberService = appConfig.memberService();
        // OrderService orderService = appConfig.orderService();

        // member를 만들고 회원가입까지 완료했음
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 1번 회원이 10000원 짜리 물건을 주문함
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
        System.out.println("order = " + order.calculatePrice()); // 할인된 금액이 출력됨


    }
}
