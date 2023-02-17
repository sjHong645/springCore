package com.springCore.discount;

import com.springCore.member.Member;

public interface DiscountPolicy {

    // 할인 대상 금액
    int discount(Member member, int price);
}
