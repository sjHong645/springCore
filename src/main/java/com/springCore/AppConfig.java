package com.springCore;

import com.springCore.discount.DiscountPolicy;
import com.springCore.discount.RateDiscountPolicy;
import com.springCore.member.MemberService;
import com.springCore.member.MemberServiceImpl;
import com.springCore.member.MemoryMemberRepository;
import com.springCore.order.OrderService;
import com.springCore.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 어플리케이션의 config를 담당하는 걸 표시하는 어노테이션
public class AppConfig {

    // 각 메소드에 Bean을 추가 ==> 각각의 메소드가 Bean 컨테이너에 추가됨

    // 이름을 바꾸고 싶다면
    // @Bean(name ="원하는 이름") 으로 설정할 수 있음
    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }


}
