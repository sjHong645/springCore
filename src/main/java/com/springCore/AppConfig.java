package com.springCore;

import com.springCore.discount.DiscountPolicy;
import com.springCore.discount.RateDiscountPolicy;
import com.springCore.member.MemberService;
import com.springCore.member.MemberServiceImpl;
import com.springCore.member.MemoryMemberRepository;
import com.springCore.order.OrderService;
import com.springCore.order.OrderServiceImpl;

public class AppConfig {

    // 전체적인 역할이 한 눈에 보이도록 리팩토링한다.

    // memberRepository를 위해서 MemoryMemberRepository를 쓸 것이다

    // 이렇게 각 메소드의 "역할"이 드러나고
    // "역할들의 구현체"가 한 눈에 들어오도록 리팩토링 했다.
    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }


}
