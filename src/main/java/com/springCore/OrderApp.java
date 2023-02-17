package com.springCore;

import com.springCore.member.Grade;
import com.springCore.member.Member;
import com.springCore.member.MemberService;
import com.springCore.member.MemberServiceImpl;
import com.springCore.order.Order;
import com.springCore.order.OrderService;
import com.springCore.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        // MemberService memberService = new MemberServiceImpl();
        // OrderService orderService = new OrderServiceImpl();
        AppConfig appConfig = new AppConfig();

        // appConfig의 메소드 memberService를 통해서...
        // new MemberServiceImpl(new MemoryMemberRepository())가 return 되도록 함

        // MemoryMemberRepository를 MemberServiceImpl에 주입한 상태에서
        // MemberServiceImple의 기능을 사용할 수 있게 되었다

        // orderService를 통해서...
        // new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy())를 return함

        // MemoryMemberRepository, FixDiscountPolicy를 OrderServiceImple에 주입한 상태에서
        // OrderServiceImple의 기능을 사용할 수 있게 되었다.

        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

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
