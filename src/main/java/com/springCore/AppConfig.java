package com.springCore;

import com.springCore.discount.FixDiscountPolicy;
import com.springCore.member.MemberService;
import com.springCore.member.MemberServiceImpl;
import com.springCore.member.MemoryMemberRepository;
import com.springCore.order.OrderService;
import com.springCore.order.OrderServiceImpl;

public class AppConfig {

    // 각각의 클래스에 구현체를 주입하기 위해서
    // 아래와 같은 코드를 작성함

    // memberRepository 인터페이스의 구현체 MemoryMemberRepository()를 주입
    // Discount 인터페이스의 구현체 FixDiscountPolicy()를 주입
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
