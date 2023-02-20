package com.springCore.order;

import com.springCore.discount.DiscountPolicy;
import com.springCore.discount.FixDiscountPolicy;
import com.springCore.discount.RateDiscountPolicy;
import com.springCore.member.Member;
import com.springCore.member.MemberRepository;
import com.springCore.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 생성자를 통해서
    // 원래는 NULL 이었던 memberRepository, discountPolicy를
    // 외부에서 구현체를 주입할 수 있도록 함

    // 이제부터 '실행'에만 집중할 수 있게 됨
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discount = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discount);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
