package com.springCore.discount;

import com.springCore.member.Grade;
import com.springCore.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; // 할인 금액

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }

        else return 0;
    }
}
