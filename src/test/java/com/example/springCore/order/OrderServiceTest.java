package com.example.springCore.order;

import com.springCore.AppConfig;
import com.springCore.member.Grade;
import com.springCore.member.Member;
import com.springCore.member.MemberService;
import com.springCore.member.MemberServiceImpl;
import com.springCore.order.Order;
import com.springCore.order.OrderService;
import com.springCore.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {

        // member를 만들고 회원가입까지 완료했음
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 1번 회원이 10000원 짜리 물건을 주문함
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // 1000원을 제대로 할인했는지 확인
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
