package com.springCore.order;

import com.springCore.discount.DiscountPolicy;
import com.springCore.member.Member;
import com.springCore.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // @RequiredArgsConstructor를 붙여줌으로써
    // final이 붙은 변수에 대해
    // 아래의 생성자를 자동으로 만들어줌

    // 즉, 아래의 코드를 쓰지 않아도 되고 값 설정을 원하는 변수에만 final을 붙여주면 된다.
    /*public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    // 의존관계를 추가하고 싶다면... final 변수만 추가해주면 됨

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
